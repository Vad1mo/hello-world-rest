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
docker run -ti --rm -p 5050:5050 vad1mo/hello-world-rest
```
Be patient, because it takes some time until all dependencies are downloaded and the container actually repsonses to requests.

## Example 

```bash
> curl localhost:5050/foo/bar
/:path1/:path2 - Hello to foo/bar ! Host:0bd595c5b46a/172.18.0.3
```


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
## Compose Example

```
  version: '2'
  services:
#---#
    proxy:
      image: traefik:1.3-alpine
      command: --web --docker --docker.domain=docker.localhost --logLevel=DEBUG --docker.endpoint=unix:///var/run/docker.sock
      ports:
        - 80:80
        - 443:443
      labels:
        - traefik.enable=false
      volumes:
        - /var/run/docker.sock:/var/run/docker.sock
        - /dev/null:/traefik.toml
#---#
    service_web:
      image: vad1mo/hello-world-rest
      hostname: web
      labels:
        - traefik.backend=web
        - traefik.port=5050
        - traefik.frontend.rule=Host:sample.127.0.0.1.xip.io; Path:/api/webhook, /api/token ,PathPrefix:/
        - traefik.frontend.entryPoints=http
        - traefik.frontend.priority = 1
#---#
    service_api:
      image: vad1mo/hello-world-rest
      hostname: api
      labels:
        - traefik.backend=api
        - traefik.port=5050
        - traefik.frontend.rule=Host:sample.127.0.0.1.xip.io; PathPrefix:/v2/
        - traefik.frontend.entryPoints=http
        - traefik.frontend.priority = 10
```
