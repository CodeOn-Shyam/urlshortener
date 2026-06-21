function shortenUrl(){
    const originalUrl = document.getElementById("urlInput").value;
    fetch('/shorten',{
        method:'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify({
            originalUrl: originalUrl
        })
    })
    .then(response => response.text)
    .then(data =>{
        document.getElementById("shortenedUrl").innerText=
        "Shorten url"+ data;
    }).catch(error=>console.error(error));
}