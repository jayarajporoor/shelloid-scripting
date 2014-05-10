call antlr4  ShelloidLexer.g4
call antlr4 ShelloidParser.g4
javac  *.java
call grun Shelloid script -gui ..\..\test\grammar\event1.shd 
del *.class
del *.java