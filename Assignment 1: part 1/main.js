const singleThreadBtn = document.getElementById("singleThreadBtn");
const multiThreadBtn = document.getElementById("multiThreadBtn");
const cancelBtn = document.getElementById("cancelBtn");
const progress = document.getElementById("progress");
const result = document.getElementById("result");
const limitInput = document.getElementById("limitInput");
const coreSelect = document.getElementById("coreSelect");


let workers = [];
let startTime;


function calculatePrimes(limit) {
    const primes = [];
    for (let i = 2; i <= limit; i++) {
        let isPrime = true;
        for (let j = 2; j * j <= i; j++) {
            if (i % j === 0) {
                isPrime = false;
                break;
            }
        }
        if (isPrime) primes.push(i);
    }
    return primes;
}


singleThreadBtn.addEventListener("click", () => {
    const limit = parseInt(limitInput.value, 10);
    if (isNaN(limit) || limit <= 0) {
        progress.textContent = "Please enter a valid positive number.";
        return;
    }

    progress.textContent = "Calculating...";
    result.textContent = "";
    startTime = performance.now();

    setTimeout(() => {
        const primes = calculatePrimes(limit);
        const endTime = performance.now();
        progress.textContent = `Done in ${((endTime - startTime) / 1000).toFixed(2)} seconds (Single-Threaded).`;
        result.textContent = `Found ${primes.length} primes.`;
    }, 0); 
});



multiThreadBtn.addEventListener("click", () => {
    const limit = parseInt(limitInput.value, 10);
    const workersCount = parseInt(coreSelect.value, 10); 
    if (isNaN(limit) || limit <= 0) {
        progress.textContent = "Please enter a valid positive number.";
        return;
    }

    progress.textContent = "Calculating...";
    result.textContent = "";
    cancelBtn.disabled = false;
    startTime = performance.now();

    const rangePerWorker = Math.ceil(limit / workersCount);

    workers = Array.from({ length: workersCount }, (_, index) => {
        const worker = new Worker("worker-thread.js");
        worker.postMessage({
            start: index * rangePerWorker + 1,
            end: Math.min((index + 1) * rangePerWorker, limit),
        });
        return worker;
    });

    let totalPrimes = [];
    let completedWorkers = 0;

    workers.forEach((worker, index) => {
        worker.onmessage = (event) => {
            totalPrimes = totalPrimes.concat(event.data);
            completedWorkers++;

            if (completedWorkers === workersCount) {
                const endTime = performance.now();
                progress.textContent = `Done in ${((endTime - startTime) / 1000).toFixed(2)} seconds (Multi-Threaded with ${workersCount} cores).`;
                result.textContent = `Found ${totalPrimes.length} primes.`;
                cancelBtn.disabled = true;

                workers.forEach((w) => w.terminate());
                workers = [];
            }
        };

        worker.onerror = (error) => {
            console.error(`Error in worker ${index + 1}:`, error.message);
            progress.textContent = "An error occurred in one of the workers.";
            cancelBtn.disabled = true;

            workers.forEach((w) => w.terminate());
            workers = [];
        };
    });
});

cancelBtn.addEventListener("click", () => {
    console.log("Cancel button clicked.");
    workers.forEach((worker) => {
        console.log("Terminating worker:", worker);
        worker.terminate();
    });
    workers = [];
    progress.textContent = "Operation canceled.";
    cancelBtn.disabled = true;
});

