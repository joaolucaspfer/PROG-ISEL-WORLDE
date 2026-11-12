cd "C:\Users\Admin\Desktop\ISEL\Prog ISEL\CanvasLib"

& "C:\Gradle\gradle-8.5\bin\gradle.bat" clean jvmJar

kotlin -cp "build\libs\CanvasLib-jvm-1.0.2.jar" MainKt