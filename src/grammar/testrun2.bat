call antlr4 ShelloidLexer.g4
call antlr4 ShelloidParser.g4
javac *.java
call grun Shelloid script ..\..\test\grammar\sample-1.shd