# Hello World REST Service

Simple REST Service that echos the requests back.
This service runs inside a container so there is almost no overhead in getting started. 

## What is this sevice good for?
- Easy test reverse proxy routes and paths.
- Use this service as a dummy to see if request reach your service correctly. 
- Use it to test load balancers by peeking at the out and see the host/ip. 
- Use it for Hello World demo projects that respond the request back. 

## Start 

```
docker run -ti --rm -p 5050:5050 vad1mo/hello-world-service
```
Be patient, because it takes some time until all dependencies are downloaded and the container actually repsonses to requests.

## Usage


Send request to `curl localhost:5050/xx/yy` and see it bouncing back.

## Responses

```
get {
    render "/ - Hello World! Host:${InetAddress.getLocalHost()}"
}
get(":name") {
    render "/ - Hello $pathTokens.name! Host:${InetAddress.getLocalHost()}"
}
get("/:path1/:path2") {
    render "/:path1/:path2 - Hello to $pathTokens.path1/$pathTokens.path2 ! Host:${InetAddress.getLocalHost()}"
}
get("/:path1/:path2/:path3") {
    render "/:path1/:path2/:path3 - Hello to $pathTokens.path1/$pathTokens.path2/$pathTokens.path3 ! Host:${InetAddress.getLocalHost()}"
}
       ```
