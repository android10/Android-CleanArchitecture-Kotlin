package scripts

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}