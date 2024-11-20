const downloadList = document.getElementById("download-list");
const addDownloadButton = document.getElementById("addDownload");
const videoUrlInput = document.getElementById("videoUrl");

addDownloadButton.addEventListener("click", () => {
  const url = videoUrlInput.value.trim();
  if (!url) {
    alert("Please enter a valid URL.");
    return;
  }
  
  createDownloadItem(url);
  videoUrlInput.value = "";   
});

function createDownloadItem(url) {
  const itemId = `download-${Date.now()}`; 
  const worker = new Worker("worker.js");
  let isPaused = false;

  
  const downloadItem = document.createElement("div");
  downloadItem.className = "download-item";
  downloadItem.innerHTML = `
    <p><strong>${url}</strong></p>
    <div class="progress-container">
      <div class="progress-bar" id="progress-${itemId}"></div>
    </div>
    <button class="start" id="start-${itemId}">Start</button>
    <button class="pause" id="pause-${itemId}" disabled>Pause</button>
    <button class="resume" id="resume-${itemId}" disabled>Resume</button>
  `;

 
  downloadList.appendChild(downloadItem);

  const startButton = document.getElementById(`start-${itemId}`);
  const pauseButton = document.getElementById(`pause-${itemId}`);
  const resumeButton = document.getElementById(`resume-${itemId}`);
  const progressBar = document.getElementById(`progress-${itemId}`);

  
  startButton.addEventListener("click", () => {
    worker.postMessage({ action: "start", url, id: itemId });
    startButton.disabled = true;
    pauseButton.disabled = false;
    resumeButton.disabled = true;
  });


  pauseButton.addEventListener("click", () => {
    isPaused = true;
    worker.postMessage({ action: "pause", id: itemId });
    pauseButton.disabled = true;
    resumeButton.disabled = false;
  });


  resumeButton.addEventListener("click", () => {
    isPaused = false;
    worker.postMessage({ action: "resume", url, id: itemId });
    resumeButton.disabled = true;
    pauseButton.disabled = false;
  });

  worker.onmessage = (e) => {
    const { type, progress, blob, error } = e.data;

    if (type === "progress") {
      progressBar.style.width = `${progress}%`;
    } else if (type === "completed") {
      const blobUrl = URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = blobUrl;    
      link.download = `video-${itemId}.mp4`;
      link.textContent = "Download File";
      link.style.display = "block";
      downloadItem.appendChild(link);
      pauseButton.disabled = true;
      resumeButton.disabled = true;
    } else if (type === "error") {
      alert(`Error: ${error}`);
      startButton.disabled = false;
      pauseButton.disabled = true;
      resumeButton.disabled = true;
    }
  };
}
