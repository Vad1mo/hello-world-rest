@Grapes([
        @Grab('io.ratpack:ratpack-groovy:1.4.4'),
        @Grab('org.slf4j:slf4j-simple:1.7.22')
])

import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
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
    }
}