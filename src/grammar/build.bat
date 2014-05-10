call antlr4 -package "com.shelloid.script.parser" ShelloidLexer.g4
call antlr4 -package "com.shelloid.script.parser" ShelloidParser.g4
javac -d ..\..\classes *.java
REM call grun Shelloid script ..\..\test\grammar\sample-1.shd