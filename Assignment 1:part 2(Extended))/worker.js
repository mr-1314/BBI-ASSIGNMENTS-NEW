let paused = false;
let receivedLength = 0;  
let chunks = [];  
let abortController = null;  

  self.onmessage = async (e) => {
    const { action, url, id } = e.data;

  
    if (action === "pause") {
      paused = true;
      if (abortController) abortController.abort();  
      return;
    }

  
    if (action === "resume") {
      paused = false;
      await downloadFile(url, id);  
      return;
    }

    
    if (action === "start") {
      paused = false;
      receivedLength = 0;  
      chunks = [];  
      await downloadFile(url, id);  
    }
  };

async function downloadFile(url, id) {
  try {
    abortController = new AbortController();  
    const headers = receivedLength > 0 ? { Range: `bytes=${receivedLength}-` } : {};  
    const response = await fetch(url, { signal: abortController.signal, headers });

    if (!response.ok) throw new Error(`HTTP Error: ${response.statusText}`);

    const reader = response.body.getReader(); 
    const contentLength = +response.headers.get("Content-Length") + receivedLength;
    while (!paused) {
      const { done, value } = await reader.read();  

      if (done) break;  

      chunks.push(value);  
      receivedLength += value.length; 

      const progress = Math.round((receivedLength / contentLength) * 100); 
      self.postMessage({ type: "progress", progress, id });  
    }

    if (!paused) {
      const blob = new Blob(chunks);
      self.postMessage({ type: "completed", blob, id }); 
    }
  } catch (error) {
    if (!paused) self.postMessage({ type: "error", error: error.message, id });
  }
}
