# akka-http.g8

This is a [Giter8](https://github.com/foundweekends/giter8) template for an Akka HTTP microservice based on the [original Activator seed by yeghishe](https://github.com/yeghishe/minimal-scala-akka-http-seed). It contains some nice features such as using the [sbt-revolver](https://github.com/spray/sbt-revolver) plugin for easy start/stopping of the microservice plus restart triggered by changes in the source.

## Usage:

```
sbt new silvaren/akka-http.g8
```

You will be presented with some prompts for package names and other settings. You can just press enter to choose the defaults, though you might want to change at least the package name to your own.

After the project is generated from the template, you can start your microservice with:
```
sbt
```
and then:
```
re-start
```
By default it will run on port 9000, and you can test it by acessing `http://localhost:9000/v1/status` on your browser or Postman. 
