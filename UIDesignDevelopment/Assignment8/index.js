const http = require('http');

const PORT = 8080;
http.createServer(() => {
    console.log(`server created successfully!! on port ${PORT}`);
}).listen(PORT);