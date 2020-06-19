javac -d bin -cp src src\space\*.java
jar cfe Space.jar space.Main -C bin . res
java -jar Space.jar