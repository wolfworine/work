---
id: arq-028
source_file: "object-oriented-programming-with-java-2147483648-2147483647.pdf"
words: 39599
---

Object Oriented Programming with
JAVA
Hanumanth Ladwa

Dedicated to my Parents and Sisters
Contents
Chapter
Chapter Title Page no.
no.
1 Fundamentals of object oriented programming 1 – 7
2 Evolution of java 8 – 14
3 Overview of java 15 – 27
4 Data types 28 – 35
5 Operators 36 – 48
6 Selection and iterative statements 49 – 68
7 Class 69 – 106
8 Arrays strings and vectors 107 – 130
9 Packages 131 – 138
10 Programming threads 139 – 158

11 Java exceptions 159 – 177
12 Applet programming 178 – 188
13 Graphics programming 189 – 199
14 Event handling 200 – 250
15 Swing 251 – 259
16 File handling 260 – 267
LAB ASSIGNMENTS 268 - 325

CHAPTER 1
FUNDAMENTALS OF OBJECT ORIENTED
PROGRAMMING
Introduction: As we move ahead with the new programming languages, it
indicates that we are improving the previous version of the same programming
language or creating a new programming language to tackle the overheads of
existing programming language and making it more flexible and user friendly.
The programmer must be able to write the programs in such a way that the
program should take optimum memory space and increase its execution speed.
The drawbacks of procedural programming and structured programming
language are eliminated as it has certain loopholes in the programming
techniques and it makes a program too complex. Hence, in order to eliminate the
limitations of the structured and procedural programming techniques
programmers have developed a new programming approach called Object
Oriented Programming (OOP) that emphasizes data rather than procedure. Each
problem is treated as an object rather than procedure. This new programming
technique uses the bottom-up approach. Some of the programming languages
that uses OOP technique is, C++, Small Talk, VC++, Simula 67, C# and Java.
Object Oriented Programming Paradigm: In object oriented programming
paradigm every problem is viewed as an object rather than the procedure. Hence,
the object is a combination of data and methods that operate on data. Some of
the features of object oriented programming language is as follows:
- Focus is one data rather than the procedure.
- Programs are decomposed into objects.
- Data can be protected by the external non-member functions.
- Objects may communicate with each other through methods.
- OOP follows a bottom-up approach.
The naming conventions used in Procedural Oriented Programming (POP) and
Object Oriented Programming (OOP) are listed as below.
Procedural oriented Object oriented
programming (POP) programming (OOP)
Variable Objects

User-defined data types Classes
Structure members Instance variables
Functions Methods
Function call Message passing
Difference between procedural oriented programming and object-oriented
programming.
Procedural oriented Object oriented
programming programming
1. Emphasizes on the 1. Emphasizes on the data
procedure rather than rather than the
data. procedure.
2. Data is not secured. 2. Data is secured.
3. Uses a top-down 3. Uses bottom-up
approach. approach.
4. It does not model real 4. It models real world
world entities. entities.
5. Programs are 5. Programs are
decomposed into decomposed into objects.
functions or procedures.
OOP emphasizes on data rather than procedure. Every given problem is viewed
as an object rather than procedure. The ideology here is to unite both data and
functions that operate on data into a single unit called object.
Concepts of OOP:
- Objects
- Class
- Data abstraction
- Data encapsulation
- inheritance
- polymorphism
- dynamic binding
- message passing
Object: Object is an identifiable entity with some characteristics and behaviour.
Object may be a person, place or table of data. Let us take an example, a mobile

cell phone consisting of its features such as color, weight, price etc. are data
whereas a mobile cell phone is used to make an audio or video call, take
photographs, make videos etc are the operations performed are called functions.
Each object is having both of these two features i.e. data and functions. An
object is any real-world entity that has its existence has both features called data
members and operations called function members.
- Example, apple, orange, banana etc are the objects of the group called
fruit.
- Similarly, roses, jasmine, lilies etc are the objects of the group called
flowers.
Object: student
Data members
Rollno.
Name
percentage
Methods
getdata()
putdata()
Class: class is a fundamental building block of object-oriented program which
serves as a blueprint. Class is a user defined data type. Class acts as a template
using which we will be able to create any number of objects of that type. A class
is a collection of similar objects that share common features.
Example: in the above example of objects, the group of fruit is called class. A
group of flowers is called class.
Similarly, the solar system is a class whereas, an individual planet in a solar
system is called an object.
class
Data members
Data1
Data2
…
Data-n

Methods
Method 1
Method 2
…
Method-n
Data abstraction: the act of including only essential details of an entity without
including background details about it is called data abstraction.
Example, let us consider the mobile cell phone with attributes such as, color,
Bluetooth, mobile cell phone camera etc. we are not considering how Bluetooth
works, or how the camera works or even how the circuit inside the mobile is
designed and what is happening inside. We are interested only in using the
mobile cell phone and its features. Therefore, it is required to know how to
operate a mobile cell phone rather than its internal working, the internal working
is hidden from the user is called abstracted data.
Data encapsulation: the process of wrapping up data and functions into a single
unit called class is called data encapsulation. Data encapsulation makes data
hidden; data hiding is an act of insulating data from outside programs. The data
is not accessible directly to the outside world, except for the functions defined
inside the class definition. These function members provide an interface between
the data and outside world to access the data out of class.
Inheritance: it is a process of acquiring properties from an object of one class to
properties of an object of another class. The class that inherits the properties to
the other class is called base class or parent class or super class.
The class that inherits the properties from the other class is called derived class
or child class or subclass.
The programmers can add new features to the already existing class without
modifying that class. This is achieved by deriving a new class from the already
existing class. The derived class has its base class features, in addition to that it
has its own features.

Here, the employee is the base class whereas manager, supervisor and clerk are
derived classes. Employee class inherit properties to the derived class and
manager, supervisor and clerk inherited by the base class employee. In addition
to the properties inherited from the base class, derived classes manager,
supervisor and clerk have their own properties.
Polymorphism: it is an ability of the message to process in more than one form.
Poly means many, morph means forms. Polymorphism means many forms. The
best examples for polymorphism are, operator overloading and function
overloading.
The process of making an operator exhibit different behaviors in different
instances is known as operator overloading.
Example: consider an addition operator, when used with numbers, acts as an
arithmetic operator to perform addition. Let’s say, 2+3 gives 5 whereas the same
operator is used with the strings let’s say “Lot”+”us” then it combines two
strings together, it acts as a string concatenation operator which results in
“Lotus”. This different behaviour of the same operator at different instances is
known as operator overloading.

The process of two or more functions having the same name with different return
types or different number of arguments is called function overloading.
Here paint() items are single interface and paint() furniture, paint() vehicle,
paint() building and paint() fabric are many methods.
Example:
int add (int a, int b);
float add (float x, float y) ;
Here, add is a function name which is common in both the functions but return
types of these two functions are different, one is int and another is float. And the
number of arguments is the same in both the functions i.e. 2. Function
overloading is used when we want to perform the same operations on different
data. Function overloading has the same interface but many methods. You can
observe in the above example add is the function name i.e. interface and many
methods means we have addition operations to be performed on two different
data types.

Dynamic binding: binding is the process of providing reference between more
than one module. Dynamic binding means the code associated with a given
procedure call is not known until the time of the call at run-time or at the time of
program execution.
Message passing: message passing is a request for execution of a procedure for
an object. Therefore, a message invokes a procedure in the receiving object to
generate the desired output.
For example, consider an object obj and we have calculated the ‘sum’
(information) by taking add (message) then it is possible to pass message to this
obj object as shown below:
Advantages of OOPs
- Using class and objects, programs are modularized.
- Linking code and object allows related objects to share common code. This
reduces code duplication and code reusability.
- As the data is encapsulated along with functions, the non-member functions
cannot access or modify data. Thus, providing data security.
- Complexity of the program development is reduced through the use of
inheritance.
- Reduces time, as creation and implementation of OOP code is easy.
- Through message passing OOP communicates to the outside system.
Applications of OOP:
- Computer graphic applications.
- CAD/CAM software.

- Object oriented databases.
- User interface design such as windows.
- Real-time systems.
- Simulation and modelling.
- Artificial intelligence and expert systems.
- Hypermedia, expert text and hypertext.
- Decision support systems and office automation systems.
- Parallel programming and neural networks.
EXERCISES
1. What are the features of OOP?
2. List the naming conventions used in POP and OOP.
3. Differentiate between POP and OOP.
4. Explain the basic concepts of OOP.
5. Describe the advantages of OOP.
6. List the applications of OOP.

CHAPTER 2
EVOLUTION OF JAVA
Java history:
Java is a general purpose object oriented programming language developed by
Sun Microsystems of USA in 1991. Earlier java was called oak (because it is a
symbol of strength and chosen as a national tree of many countries) by James
Gosling (One of the inventors of the language) who is known as Father of Java.
In 1995 Oak was renamed as Java. Java is an island in Indonesia. Developed this
language to make it platform independent meaning that the programs written in
java can be executed on any of the systems irrespective of hardware and
operating system. Java programs are written and executed on any of the
computer systems, because java produces the intermediate code called bytecode
that can be interpreted by any system as it is not tied with hardware and software
configuration of the computer system. The team members of java also known as
Green Team, initiated the project to develop a programming language for digital
devices like, set-top box, interactive television etc. It was best suited for internet
programming. Presently, Java is used in internet programming, like mobile
devices. Electronic games, electronic business solutions etc.
Features of Java:
The primary objective of developing Java programming language was to make it
portable, simple and secure programming language. In addition to this, there are
some other features of Java as described below.
- Simple: Java is simple, easy to learn and easy to understand because Java
syntax is similar to C++ programming language. Java is made simple by
removing some complicated features of C++ such as explicit pointers,
friend functions, inline functions, goto statements, operator overloading
etc. Java allocates and deallocates memory for the object at runtime hence,
the objects are allocated dynamically and deleted by automatic garbage
collection once the program execution terminates.
- Object Oriented: Everything in java is considered as an object. The
software is organized as a combination of different types of objects that
incorporate both data and behavior. Object oriented methodology makes
the software development process easy and provides certain rules for

maintenance.
- Platform independent: Java is not dependent on machine hardware as
well as software. It is completely independent of any platforms. A
platform is the hardware or software environment in which a program
runs. Java can be executed on multiple platforms, for example, windows,
Linux, sun Solaris, Mac/OS, etc. Java is compiled by the compiler and
converted into byte code. This byte code is also called as intermediate
code that can be interpreted by the Java Virtual Machine (JVM). This byte
code can be executed on any of the platforms irrespective of their
configurations. Hence, java is a portable programming language. Java
programs can be easily moved from one computer system to another. Java
is architecture neutral, in C programming, int data type takes 2 bytes of
memory for 32 bit architecture and 4 bytes for 64 bit architecture.
However, it takes 4 bytes of memory for both 32 and 64 bit architecture in
Java.
- Secured: Java is known for security. As java is used to develop internet
applications called applets, java ensures that no viruses are communicated
with an applet. As java does not support pointers, it ensures that the
programs cannot gain access to memory locations without proper
authorization.
- High performance: Java is both a compiled and interpreted programming
language. Java compiler produces an intermediate code called byte code.
This byte code can be executed on any of the computer systems. This
intermediate code of java makes the java program execute the program
faster. Hence, provides high performance.
- Robust: java has the ability to use strong memory management. As there
is no use of pointers in java avoids security issues. It provides garbage
collection which runs on the java virtual machine to delete the unwanted
objects that are no longer required. Java strongly checks for type and has
an exception handling mechanism. Hence, makes Java a robust
programming language.
- Distributed: Java is designed to distribute the applications on the
networks. Java applications allows programmers to access remote objects
on the internet, enables multiple programmers at remote locations to
collaborate with one another and work together on a single project.
- Multi-threaded: A thread is a sequence of finite sets of instructions
written as a separate program, executing concurrently. If multiple threads

are to be executed, then the advantage of using multi thread is that, instead
of allocating memory for each thread, it shares the common memory
location. Threads are used for multimedia, web applications etc.
- Dynamic: Java is capable of dynamically linking in new class libraries,
methods and objects. Java supports functions written in C and C++
programming languages. These functions are known as native methods.
These methods are linked dynamically at runtime. Even the objects are
allocated memory space at the time of program execution and frees up the
memory locations by the built-in system called garbage collection, as soon
as the program terminates its execution.
How does Java differ from C?
Some of the features of the C programming language that causes problems are
eliminated from java. Those features are list as below:
- The user defined data types structures and unions are not included.
- In java, header files or preprocessor directives are not required.
- No sizeof and typedef statements are used.
- Java does not support the type modifiers such as auto , extern , register ,
signed and unsigned .
- The goto statement is omitted.
- Java does not support explicit pointers.
- Java adds new operators such as instanceof and >>>.
- Java adds labeled break and continue statements.
- Functions in java that accept no parameters must be declared with empty
brackets and not using void .
How does Java differ from C++?
Some of the features present in C++ programming language are eliminated in
java that are listed as below.
- Java does not support operator overloading.
- Java does not use default arguments.
- Java doesn’t support multiple inheritance (instead java uses interface).
- Java does not support global variables. Every variable and method must be
declared in a class.
- Java does not support destructor functions. Instead, java uses a method
called finalize().
- Java is both a compiled and interpreted programming language. Java

source code is converted into byte code at compile time and the interpreter
executes this bytecode at runtime and produces output.
Java and internet: Java is associated with the internet. Web browsers to run
applets on the internet. Java programming language is used to create applet
programs and run them locally using a java enabled browser such as hot java,
used to download an applet located on the internet and it on the local computer
system. Internet users can set their websites containing java applets that could be
used by other remote users of the internet. Because of this fact, java is also
known as an internet language.
Java and World Wide Web: java communicates with a web page through a
special tag called <applet>. Java users send a request for an HTML document to
the remote computers and browser. The web browser is a program that accepts a
request, processes the request and sends the required documents to the local
computer. The document contains the applet tag which identifies the applet. The
corresponding applet is transferred to the local computer. The java enabled
browser on the local computer interprets the byte code and provides output.

www is a collection of information stored on the internet. It is an information
retrieval system. www contains web pages that provide both information and
controls. Web pages contain HTML tags that enable us to retrieve, manipulate
and display documents across the globe. Java enables web pages to display
animation, graphics, and special effects. The following figure depicts exactly
how the local computer system user communicates to the java applet on the
remote computer system.

Web browsers: web browser is an application software used to locate, fetch and
show the content on the world wide web. Web browser acts as a client whereas
the remote computer holding information acts as a web server. Web browser
sends a request object for information to the remote web server. The web server
responds back by sending a response object to the web browser that contains
information. The information fetched from the server contains HTML tags that
are interpreted by the browser. Some of the examples for web browsers are, hot
java, Netscape navigator, internet explorer, google chrome, firefox mozilla etc.
Java environment: java environment includes a large number of development
tools and number of classes and methods. The development tools are present in a
unit called Java Development Kit (JDK) and the classes and methods are present
in a unit called Java Standard Library (JSL) or Application Programming
Interface (API)
Java Development Kit:
Tools Description
Applet Enables to run java applets.
viewer
Java Java interpreter which runs both applet
and application by interpreting byte code.
Javac The java compiler which translates java
source code to byte code.
Javadoc Create HTML format documentation.
Javah Produces header files for use with native
methods.
Javap Java disassembler enables to convert byte
code into program description.
Jdb Java debugger helps to find errors in the
program.
Application Programming Interface (API): most commonly used packages are
listed below:
Language support package: it is a collection of classes and methods required
for implementing basic features of java.

Utilities package: it is a collection of classes to provide utility functions such as,
date and time functions.
Input-output package: it is a collection of classes required for input-output
manipulation.
Networking package: it is a collection of classes for communicating with other
computers via the internet.
Applet package: it is a collection of classes that allows one to create a java
applet.
The following diagram depicts the process of building and running a java
application program.

Java Runtime Environment: java runtime environment facilitates the execution
of programs. It consisting of the following:
- Java Virtual Machine (JVM): JVM interprets the byte code and produces
an output. This byte code is also called an intermediate code that can be
executed on any system. It follows the Write Once and Read Anywhere
(WORA) principle.
- Runtime class libraries: it is a collection of class libraries used to execute
java programs.
- User interface toolkits: Abstract Window Toolkit (AWT) and swing
support various input methods to interact with the application program.
- Deployment tools: Java Runtime Environment consisting the following
tools.
Java plug-in: This plug-in enables the execution of a java applet on
the browser.
Java web start: This tool enables remote deployment of an
application.

EXERCISES
1. Explain the evolution of java.
2. Describe the features of java.
3. How does Java differ from C?
4. How does Java differ from C++?
5. Explain how java and the internet are related.
6. Explain the relation between java and www.
7. What are web browsers? Explain with examples.
8. Explain the tools available in JDK.
9. Describe API with its components.
10. Briefly explain Java Runtime Environment.

CHAPTER 3
OVERVIEW OF JAVA
Introduction: Java is a general purpose, object oriented programming language.
Java programming language is used to develop two types of programs.
- Standalone applications
- Web applets
Standalone applications are those programs that are executed on the local
computer system whereas web applets are developed for internet applications.
An applet is a program situated on a remote computer system (server) that can be
downloaded and executed on a local computer (client). Let us discuss java
standalone applications first.
Java program structure:

Document section: in this section, a textual information for the other
programmer to understand the purpose of the statements or program can be
written either in a single line comment (begins with //) or multiline comment
(/*comment text here*/).
Package statement: package consisting of numerous classes that tells the
compiler to import into our program. The classes that are present in the package
that are to be utilized in our program can be imported.
Import statements: import statement tells the compiler to include certain
packages that consist of classes that are to be utilized in our program. import
statement is similar to #include statements in C and C++ programming
languages.
Example: import employee.Sample;
This import statement tells the compiler to import or include the class called
Sample present in the employee package.
Interface statements: In java we cannot implement multiple inheritance, instead
we use interfaces. It contains a collection of method declarations.
Class definition: Everything in java is written in class definition only. Class is
used to map the objects of real world problems.
Main method class: Every java standalone program must contain a main
method. The main method creates objects of various classes and establishes the
communications between them. When the program terminates the control is then
transferred back to the operating system.
Simple java program:
class FirstProgram

{
public static void main(String args[])
{
System.out.println(“Welcome to the world of Java”);
}
}
Here, class is a keyword used to define a class. Everything will be enclosed
within the class definition. FirstProgram is a valid identifier name given to the
class definition. Opening brace indicates the beginning of the class body. And
respective closing brace is required for proper execution of the program. (In C++
programs class definition is used to terminate by semicolon (;) whereas in Java
the class definition is not terminated by a semi colon). public static void
main(String args[]) is a main function as in C/C++. It consists of a public access
specifier, making it accessible to all other classes. The keyword static is used by
the interpreter before any objects are created. And the keyword void specifies
that the main method does not return any value. String args[] declares a
parameter named args, which contains an array of objects of the class type
String. System.out.println(“Welcome to the world of Java ''); is similar to printf
and cout statements in C and C++ programming language. You can observe the
hierarchy. System is a class, class contains objects, cout is an object, object
contains methods, println is a method. This statement prints the string Welcome
to the world of Java.
Note: Java program must be saved with the same name as that of the class
name with the file extension .java.
Executing java program: execution of java program involves two steps:
1. Compiling source code into intermediate (byte code) using javac compiler.
2. Executing the byte code using java interpreter.
The above java program can be compiled as,
javac FirstProgram.java
When there are no errors in the program, java creates a file called
FirstProgram.class containing byte code.
To run the program type the following command.

java FirstProgram
This command interprets a standalone program and displays the output Welcome
to the world of Java.
Machine independent: The source code written in the Java programming
language is compiled and converted into byte code. The same is depicted in the
following figure.
FirstProgram.java FirstProgram.class
The java virtual machine (JVM) interprets bytecode into machine code. The
same process is depicted in the following figure.
Virtual machine Real machine
Command line arguments: In some situations we expect our program to act in a
particular way depending on the input provided at the time of execution. This is
achieved by command line arguments.
As we are familiar with args in the main function which is used as an array of
strings. Any arguments provided in the command line are passed to the array
args as its elements.
Example: java FirstProgram robust secure distributed
This command line contains three arguments. These are assigned to the array
args as follows:
args[0]=robust
args[1]=secure
args[2]=distributed

the individual elements of an array can be accessed by using an index.
Tokens: Tokens are the smallest or basic elements of a program. The program
instructions are built by using tokens. There are five types of tokens available in
java. They are,
- Keywords
- Identifiers
- Literals or constants
- Operators
- Separators
Keywords: Keywords are the reserved words that has fixed meaning to the java
compiler that cannot be changed by the programmer. These words cannot be
used as an identifier name. These words have syntactical and semantic meaning
to the compiler. As java is case sensitive language, keywords should be written
in lowercase letters only.
Some of the keywords are listed in the following table:
if case else new try finally break class
byte for import long native int interface
private static this public super
package throw catch char continue
goto final const extends do instanceof
protected switch volatile while synchronized
etc.
Identifiers: identifiers are the name given to the programming elements such as,
classes, methods, variables, objects, labels, packages and interfaces. The rules
for declaring identifier names are given below:
- Identifiers can have alphabets, digits, underscore (_) and dollar sign ($)
characters.
- Identifiers must not begin with numbers.
- Both uppercase and lowercase letters are distinct.
- Identifiers can be of any length (maximum is 256 characters).
Java follows some of the naming conventions as given below:
- Instance variables and methods are to be written in lowercase letters. If it

contains more than one word then, first letter of the second word and the
subsequent word with a leading uppercase letter.
Example: sample, avg, myName, obtainedMarks, total_marks etc.
- Classes and interfaces must begin with uppercase letters and each
subsequent word with a leading uppercase letter.
Example: Employee, Student, Account etc.
- Variables that are made constant values use all uppercase letters and
underscore between words.
Example: GRAND_TOTAL, GROSS_SALARY, TAX,
PRINCIPAL_AMOUNT etc.
Literals or constants: The quantity or value that does not change during the
execution of a program is called literals or constants. Java supports the following
types of literals.
- Integer
- Floating-point
- Character
- String
- Boolean
- Null
All of these will be discussed in detail with examples in the forthcoming
chapters.
Operators: It is a symbol that tells the compiler to perform some specific
mathematical or logical manipulation.
Operators are broadly classified as, unary, binary and ternary operators. We will
discuss each one of them in detail with examples in the forthcoming chapters.
Separators: These are the symbols used to indicate the code separation and
arrangement. Separators are listed as,
round parentheses ()
curly braces {}
square brackets []
semicolon ;
comma ,
period .

Installing and configuring java:
Step 1: download Java Development Kit (JDK) from the
https://www.oracle.com/java/technologies/javase-jdk16-downloads.html website.
Step 2: Once the download is complete, double click the .exe file to begin the
installation process as shown below.
Step 3: click on next button to begin java installation. The next custom setup
wizard appears as shown below.

Step 4: this screen allows the user to change the default installation path. Click
on the next button to continue installation. The installation progress appears as
shown below.

Step 5: Once the installation completes it shows the following dialog box.
Click on close to move further settings.
Configuring java: after installation of java, configure it by adding the java path
to the environment variable. i.e. we need to set the path. Following steps show
how to configure java.
Step 1: Right click on this pc icon (or my computer or computer icon) and click on

properties from the dropdown list. The system window appears as shown below.
Step 2: Click the advanced system settings link to open the system properties
dialog box as shown below.

Step 3: Click the environment variables button to open the environment
variables dialog box as shown below.

Step 4: The environmental variables dialog box is divided into two sections –
user variables and system variables . Under the system variables section, select
the path option below the variable column and click the edit button. The edit
system variable dialog box appears, as shown below.

Step 5: By default, the path variable is already set to multiple locations. Click on
the New button to set the java directory path in the variable value text box,
separated by a semi-colon, as shown below.

Step 6: Click OK to close the edit system variable dialog box.
Step 7: Click OK to close the environment variables dialog box.
Step 8: Click OK to close the system properties dialog box and complete the
process of configuring java.
EXERCISES
1. Explain java program structure with an example.
2. How to compile and run java programs?
3. Explain about command line arguments.
4. What is a token? Specify the types.
5. Define an identifier. Write its rules for writing.
6. Explain about literals, operators and separators.
7. Describe the steps involved in java installation.

8. Explain the steps involved in the java configuration.

CHAPTER 4
DATA TYPES
Introduction: In this chapter we will learn about constant variables and data
types that are to be used in java programs. These basic elements are necessary to
understand to learn any programming language.
Constants: Constants are the quantities that do not change during the execution
of a program.
Constants are classified as follows:
Integer constants: these are the whole numbers without any decimal point.
Integer constants are prefixed with plus or minus sign. Further integer constants
are classified as decimal constants, octal constants, hexadecimal constants and
unsigned constants.
Decimal constants: these are the constants whose base or radix of the number
system is 10. Decimal constants range from 0-9. Any big number can be
generated that consists of a combination of digits between 0 to 9.

Example, 123, -410 etc are decimal constants.
Octal constants: these are the constants whose base or radix of the number
system is 8. Octal constants range from 0-7. Any number generated must have
digits between 0-7 and octal constants always begin with 0.
Example, 0345, -0145
0825 // this integer is an invalid octal number because 8 is not an octal digit
Hexadecimal constants: these are the constants whose base or radix of the
number system is 16. Hexadecimal range from 0 – 9 and a – f or A – F.
hexadecimal digits a (or A) through f (or F) represent value from 10 through 15.
Any number generated must be between the range of 0 – 9 and a (or A) through f
(or F) and hexadecimal constants prefixed with 0x or 0X.
Example, 0x8FFFAD, -0X3ADEF etc.
Unsigned constants: to specify an unsigned type we use either u or U suffix. To
specify a long type, we use either l or L suffixes.
Example, 478u; // unsigned constant
0x7777ffL // hexadecimal long type
07524ul //octal unsigned long type
Floating-point constants: these are the constants with the decimal point and
fractional part are called floating point constants. Floating-point constants are
also called real numbers. These constants can be represented in two forms,
- Fractional form
- Mantissa exponent form
Fractional form: fractional form of floating-point numbers can be represented as
integer part followed by decimal point followed by fractional part.
Example, 20.05
20 . 05
Integer part Decimal point Fractional part
Mantissa exponent notation: any big number or any small number can be
written in the form of power of 10.
Example,
4.523e2 // 4.523 x 10 2

4.523E-2 // 4.523 x 10 -2
-0.4523e-2 // -0.4523 x 10 -2
The exponent may be specified using e or E followed by an optional sign (+ or -)
and a sequence of digits.
Character constants: a single character enclosed within a pair of single
quotation marks.
For example, ‘a’, ‘@’, ‘&’ etc.
Each of these characters are assigned with the integer constant values called
ASCII (American Standard Code for Information Interchange) values
standardized by ANSI (American National Standard Institute). For example,
ASCII value of A is 65, B is 66 and so on Z is 90 (uppercase letters), a is 97, b is
98 and so on z is 122 (lowercase letters), 0 is 48, 1 is 49 and so on 9 is 57
(digits).
There is another class of character, which are non-printable, but they have
special meaning. For example, space bar, tab button, enter button, back space
etc. such character constants are called escape sequences . This type of character
constants is always used with the output statements and these types of constants
begin with \ (backslash).
Following table shows the list of escape sequences used in Java,
Escape sequence Description
\a system alarm (beep)
\b Backspace
\f Form feed
\n New line (line feed)
\r Carriage return
\t Horizontal tab
\v Vertical tab
\” Double quote
\’ Single quote (apostrophe)
\0 Null character (end of string)
\\ Backslash itself

String constant: a string constant is a sequence of characters enclosed within a
pair of double quotation marks. Strings are sequences of arrays of characters. By
default, the compiler inserts a special symbol called null character (‘\0’) at the
end of the string.
Example, “Computer”, “C programming”, “Lotus”, etc.
“Computer” is represented as “Computer\0” in memory.
Classification of data types: data types are classified as follows.

Fundamental data types: these data types are also called as primitive data types
or basic data types or primary data types or simple data types or built-in data
types.
Fundamental data types include: int, char, float, double and void.
int: integers are whole numbers without decimal point and fractional part. Integer
numbers can be both positive and negative. Integer numbers occupy 4 bytes of
memory. The range of integers depends on the word length of the computer.
Word length means the number of bits that the CPU can process at a time. The
word length of the integer is from -2147483648 to +2147483647.
Valid integers: -120, 1500, 32000, +19000, 0 etc.
Invalid integers: 13,300 //comma is not allowed
-130.5 //decimal point is not allowed
56,000.30 //comma and decimal number is not allowed
Java supports the following three types of integers as well,
Type Size Range
byte 1 byte -128 to +127
short 2 bytes -32768 to +32767
Long 8 bytes -9223372036854775808 to +9223372036854775807
char: a single character enclosed within a pair of single quotation marks is
character constant. Characters could be a single letter, a single digit, a single
special symbol enclosed within a pair of single quotation marks. Example, ‘a’,
‘5’, ‘#’ etc. a char type takes 2 bytes of data in memory. These characters
represent a unique number called ASCII codes. The range of characters is, -128
to +127. If the characters are unsigned then, it is ranging from 0 to 255.
float: these are the numbers with the fractional part. Floating-point numbers can
be both positive and negative. Example, -85.25, 0.0, 33.20, etc. the range of
floating-point numbers is, -3.4e-38 to +3.4e+38. Floating point numbers will
occupy 4 bytes of memory.
double: these are also the floating-point numbers with the higher precision than

float type of data. The data type double will give a more accurate value up to 16
digits after the decimal point, whereas, float will give an approximate value up
to 6 digits after the decimal point. This type of data takes 8 bytes of memory.
The range of double types of data is, -1.7e-308 to +1.7e+308.
boolean: This type of data will return one of the two values, either true or false .
This type of data takes 1 bit of memory.
Variables: a variable is a quantity that changes during execution of the program.
Java encourages us to use meaningful names for variables. Variables are declared
in the declaration section before using them in the program. In the Java program,
the variable represents the memory location that holds the value assigned to it.
Declaration of a variable: the syntax for declaring a variable is,
Data_type variable_name;
Here, the variable_name is the identifier name that must satisfy all the rules for
declaring an identifier. The variables are named storage locations whose values
can be manipulated during program execution.
Some valid variables:
Roll_no data date_of_birth percentage
Some invalid variables:
float //it is a keyword
total_% //special symbol % is not allowed
2subjects //started with the digit
My result //blank space is not allowed
Initializing a variable:
The general form of initializing a variable is,
Data_type variable_name=value;
Example: int a=10;
Here, int is one of the primitive data types. And a is a variable that holds integer
type of data. The variable a is a named storage location. 10 is a constant value
assigned to the variable a of type integer. A semicolon indicated termination of
variable declaration.
How to make a value of the variable constant?

Example, int a=10;
In this example, the variable a is of type integer with value 10 is assigned to it
can be changed at any point of time. In order to make it constant we use a
keyword final before the data types as follows:
final int a=10;
now, the value of the variable a is constant, that cannot be changed during the
program execution.
Scope of variables: scope of the variable is part of the program where the
variable can be accessed. If the variable is declared inside the block then it can
be accessed within that block of code. If the variable is declared before the main
function then it can be accessed anywhere in the program. Hence, there are two
scopes for the variables viz;
Local variables: the variables declared inside the method or block of code can
be accessed within that block are called local variables.
Global variables: the variables declared before the main() method can be
accessed throughout the program are called global variables.
Data type conversion: it is a process of converting one type of data into another
type. It is also called type casting.
There are two types of type castings, they are:
- Implicit type conversion
- Explicit type conversion
Implicit type conversion: in implicit conversion, low order data type gets
converted into high order data type automatically by the compiler.
Example, int a=10;
float b=a;
Here, a is of low order data gets automatically converted into higher order data
type. b assigns value 10.0.
explicit type conversion: in explicit type conversion, high order data type gets
converted into low order data type manually by the programmer.
The general form of type casting is,
(data type) variable;

Example, int a=5;
float b;
b=1/a;
b=1/5;
b=0 (result after integer division)
since, b is of float type, the actual value should be 0.2. but it is displaying result
0. Therefore, to get the actual value, data conversion is required. This can be
achieved by rewriting the statement b=1/a as,
b=1/(float)a;
The process of assigning a smaller type to a larger type is called widening or
promotion . Assigning a larger type to a smaller type is called narrowing .
Narrowing may result in loss of information.
// Write a program to illustrate the use of type casting
class Typecast
{
public static void main(String args[])
{
char ch=’a’;
byte b=10;
short s=20;
int i=12345;
long l=12345678L;
float f=3.142f;
double d=0.000000045;
System.out.println(“(short)b=”+(short)b);
System.out.println(“(short)i=”+(short)i);
System.out.println(“(float)l=”+(float)b);
System.out.println(“(int)f=”+(int)f);
}

}
Output:
F:\java programs >javac Typecast.java
F:\java programs >java Typecast
(short)b=10
(short)i=12345
(float)l=10.0
(int)f=3
Note: floating point constants have a default type of double .
If we wish to initialize a constant value to the variable of type float,
float f=1.23;
this statement will generate error;
“incompatible type for declaration. Explicit cast needed to convert double to
float.”
The above same declaration should be written as:
float f=1.23F;
EXERCISES
1. What are constants? Explain various types of constants.
2. What are data types? Explain primitive data types with an example
for each.
3. Define a variable. Explain how to declare and initialize variables.
4. What is type casting? Explain its types with an example.
5. Write a program to illustrate the use of type casting.

CHAPTER 5
OPERATORS
Introduction: an operator is a symbol that tells the compiler what specific
mathematical or logical manipulation has to be performed. Operators in Java are
classified as below,
Unary operators: these are the operators that operate only on one operand.
Operator Name
! Logical NOT. It is used to reverse the state of operand. If a
condition is true then logical NOT return false value.
& Address-of operator. Returns address of the operand.
~ One’s complement. Convert 0 to 1 and 1 to 0
* Pointer dereference. Represent pointer type.
+ Unary plus. Represent a positive operand.
++ Increment. Increase the value of an operand by 1.
- Unary minus. Represent a negative operand.
-- Decrement. Decrease the value of an operand by 1.

Increment operator (++): ++ is used to increment the value of the operand by 1
and the operator can appear before or after the operand. If the operator prefix
operand is called pre-increment operator. If the operator suffix operand is called
post-increment operator. In case of pre-increment, the value of operand is
incremented and then used. In case of post increment, the value of operand is
used then incremented.
Example, if a=5, then a++ returns 5
If a=5, then ++a return 6
Decrement operator (--): -- used to decrement the value of the operand by 1 and
the operator can appear before or after the operand. If the operator prefix
operand is called pre-decrement operator. If the operator suffix operand is called
post-decrement operator. In case of pre decrement, the value of operand is
decremented then used. In case of post decrement, the value of operand is used
then incremented.
Example, if a=5, then a--returns 5
If a=5, then --a returns 4
Binary operators: the operator that operates on two operands is called binary
operators. Binary operators are classified as, arithmetic operators, relational
operators, logical operators, bitwise operators and assignment operators.
Arithmetic operators: the operators supported by Java are: + - * / %
Let us consider, a=10 and b=20 then
Operator Description Example
+ Adds two or more operands a+b returns 30
- Subtract second operand from the first a-b returns -10
* Multiply first and second operand a*b return 200
/ Performs integer division and returns quotient a/b return 0
% Performs modular division and returns a%b return 10
remainder
// Write a program to demonstrate an arithmetic operators
class Arithmetic
{

public static void main(String args[])
{
int a =10,b=20;
System.out.println(“a=”+a);
System.out.println(“b=”+b);
System.out.println(“a+b=”+(a+b));
System.out.println(“a-b=”+(a-b));
System.out.println(“a*b=”+(a*b));
System.out.println(“a/b=”+(a/b));
System.out.println(“a%b=”+(a%b));
}
}
Output:
F:\java programs >javac Arithmetic.java
F:\java programs >java Arithmetic
a=10
b=20
a+b=30
a-b=-10
a*b=200
a/b=0
a%b=10
It is also possible to receive input from the user through the keyboard as shown
in the following programming example.
import java.util.Scanner;
class Add
{
public static void main(String args[])

{
int a,b,c;
Scanner scan=new Scanner(System.in);
System.out.println("Enter the value for a: ");
a=scan.nextInt();
System.out.println("Enter the value for b: ");
b=scan.nextInt();
c=a+b;
System.out.println("Sum of "+a+" and "+b+" = "+c);
}
}
Output:
F:\java programs>javac Add.java
F:\java programs>java Add
Enter the value for a:
10
Enter the value for b:
40
Sum of 10 and 40 = 50
Relational operators:
The relational operators supported by Java are: ==, !=, >, >=, <, <=
Let us consider, a=10 and b=20 then:
Operator Description Example
== Compares the values of two operands equal (a==b) return
or not false
!= Compares the values of two operands equal (a!=b) return true
or not
> Checks if the value of the operand on the (a>b) return false
left is greater than the right operand.

< Checks if the value of the operand on the (a<b) return true
left is less than the right operand.
>= Checks if the value of the operand on the (a>=b) return
left is greater than or equal to the right false
operand.
<= Checks if the value of the operand on the (a<=b) return true
left is less than or equal to the right
operand.
// Write a program to demonstrate relational operators
class Relational
{
public static void main(String args[])
{
int a =10,b=20;
System.out.println(“a=”+a);
System.out.println(“b=”+b);
System.out.println(“a>b=”+(a>b));
System.out.println(“a>=b=”+(a>=b));
System.out.println(“a<b=”+(a<b));
System.out.println(“a<=b=”+(a<=b));
System.out.println(“a==b=”+(a==b));
System.out.println(“a!=b=”+(a!=b));
}
}
Output:
F:\java programs>javac Relational.java
F:\java programs>java Relational

a=10
b=20
a>b=false
a>=b=false
a<b=true
a<=b=true
a==b=false
a!=b=true
Logical	operators:	the	logical	operators	supported	by	Java	are:	&&,	||	and	!
Let	us	consider,	a=0	and	b=10	then:
| Operator |     | Description |     |     | Example |
| -------- | --- | ----------- | --- | --- | ------- |
&& Logical	AND	operator	checks	if	both	the (a&&b)	return	false
operands	are	non-zero	then	condition
becomes	true,	otherwise	false.
| ||  | Logical	OR	operator	checks	if	both	the |     |     | (a||b)	return	true |     |
| --- | -------------------------------------- | --- | --- | ------------------ | --- |
operands	are	zero	then	condition
becomes	false,	otherwise	true.
! Logical	NOT	operator	reverses	the	state !a	return	true	and	!b
|     | of	the	operand.	If	the	operand	is	true	then |     |     | return	false |     |
| --- | ------------------------------------------- | --- | --- | ------------ | --- |
it	makes	it	false	and	vice	versa

Logical	AND:	the	result	of	the	logical	AND	is	true,	when	both	the	operands	are
true;	otherwise	the	result	is	false.
|     | Operand1 |     | Operand2 | Operand1	&&	Operand |     |
| --- | -------- | --- | -------- | ------------------- | --- |
2
|     | False |     | False |     | False |
| --- | ----- | --- | ----- | --- | ----- |
|     | False |     | True  |     | False |
|     | True  |     | False |     | False |
|     | True  |     | True  |     | True  |

Logical	OR:	the	result	of	the	logical	OR	is	false,	when	both	the	operands	are
false;	otherwise	the	result	is	true.
|     | Operand1 | Operand2 | Operand1||Operand2 |       |
| --- | -------- | -------- | ------------------ | ----- |
|     | False    | False    |                    | False |
|     | False    | True     |                    | True  |
|     | True     | False    |                    | True  |
|     | True     | True     |                    | True  |
Logical	NOT:	the	result	of	the	logical	NOT	is	true	when	the	operand	is	false
and	vice	versa.
|     | Operand |     | !Operand |     |
| --- | ------- | --- | -------- | --- |
|     | False   |     | True     |     |
|     | True    |     | False    |     |
The	bitwise	operators	supported	by	C	are	listed	in	the	following	table:
| Operator |                                          | Description |     | Example       |
| -------- | ---------------------------------------- | ----------- | --- | ------------- |
| &        | Binary	AND	return	bit	1	if	both	operands |             |     | a&b=0000	1010 |
bits	are	1,	otherwise	0.
| |   | Binary	OR	return	bit	o	if	both	operands	bits |     |     | a|b=0000	1011 |
| --- | -------------------------------------------- | --- | --- | ------------- |
|     | are	0,	otherwise	1.                          |     |     | 	             |
| ^   | Binary	eXclusive	OR	(XOR)	returns	bit	0	if   |     |     | a^b=0000	1110 |
|     | there	are	odd	numbers	of	1’s.	Otherwise	1.   |     |     | 	             |
| ~   | Binary	negation	complements	the	bits.        |     |     | ~a=0000	0101  |
|     | Converts	bits	1	to	0	and	vice	versa.         |     |     | 	             |
<< Binary	left	shift	operator	shifts	the	number a<<2=0010	1000
|     | of	bits	specified	onto	the	left.         |     |     | 	              |
| --- | ---------------------------------------- | --- | --- | -------------- |
| >>  | Binary	right	shift	operator	shifts	the   |     |     | a>>2=0000	0010 |
|     | number	of	bits	specified	onto	the	right. |     |     | 	              |
Let	us	consider,	a=10	and	b=11	then	converting	into	binary,
a=0000	1010	b=0000	1011
The	result	of	bitwise	ANDing	of	a	and	b	is,
a	=	0000	1010
b	=	0000	1011

a&b = 0000 1010
the result of bitwise ORing of a and b is,
a = 0000 1010
b = 0000 1011
a|b = 0000 1011
the result of bitwise eXclusive-ORing of a and b is,
a = 0000 1010
b = 0000 1011
a^b = 0000 1110
the result of bitwise complement of a is,
a=0000 1010
~a=1111 0101
The left shift bitwise operator changes the content of the operand by shifting the
number of bits specified from left.
General form:
Operand left_shift_operator number_of_bits
Example: a<<2
The result of the bitwise left shift operator is,
Before left shifting of bits
0 0 0 0 1 0 1 0
After two bits shifted to left
0 0 1 0 1 0 0 0
The right shift bitwise operator changes the content of the operand by shifting
the number of bits specified from right.
General form:
Operand right_shift_operator number_of_bits
Example: a>>2

The	result	of	the	bitwise	right	shift	operator	is,
Before	right	shifting	of	bits
| 0   | 0   | 0   | 0   | 1   | 0   | 1   | 0   |
| --- | --- | --- | --- | --- | --- | --- | --- |
After	two	bits	shifted	to	right
| 0   | 0   | 0   | 0   | 0   | 0   | 1   | 0   |
| --- | --- | --- | --- | --- | --- | --- | --- |
Shorthand	assignment	operators:
Shorthand	assignment	operators	are	a	compact	way	of	writing	assignment
statements	in	an	expression.	The	shorthand	assignment	operator	works	on	both
arithmetic	operators	and	bitwise	operators.
The	general	form	of	a	shorthand	assignment	operator	is,
Operand	operator=expression
The	following	table	shows	the	use	of	assignment	operators:
| Operator                 |     | Assignment	statements |     |     | Shorthand	assignment |     |     |
| ------------------------ | --- | --------------------- | --- | --- | -------------------- | --- | --- |
| +	(plus)                 |     | a=a+b                 |     |     | a+=b                 |     |     |
| -		(minus) 	             |     | a=a-b                 |     |     | a-=b                 |     |     |
| *	(multiplication)       |     | a=a*b                 |     |     | a*=b                 |     |     |
| /	(integer	division)     |     | a=a/b                 |     |     | a/=b                 |     |     |
| %	(modular	division)     |     | a=a%b                 |     |     | a%=b                 |     |     |
| &	(bitwise	AND)          |     | a=a&b                 |     |     | a&=b                 |     |     |
| |	(bitwise	OR)           |     | a=a|b                 |     |     | a|=b                 |     |     |
| ^	(bitwise	XOR)          |     | a=a^b                 |     |     | a^=b                 |     |     |
| <<	(bitwise	left	shift)  |     | a=a<<b                |     |     | a<<=b                |     |     |
| >>	(bitwise	right	shift) |     | a=a>>b                |     |     | a>>=b                |     |     |

Special	operators:	the	special	operators	supported	by	C	are	listed	in	the
following	table:
| Operator |     | Description                                        |     |     |     |     |     |
| -------- | --- | -------------------------------------------------- | --- | --- | --- | --- | --- |
| sizeof() |     | It	returns	the	number	of	bytes	that	each	data	type |     |     |     |     |     |
takes.
| ,   |     | Expressions	can	be	separated	by	comma	operator |     |     |     |     |     |
| --- | --- | ---------------------------------------------- | --- | --- | --- | --- | --- |

. (dot) and -> Member access operator used to access each member
(arrow) of class, structure and union.
cast It converts one type of data to another. For example,
int(3.3) would return 3 by truncating a fractional
part.
& Ampersand operator returns the address of the
variable
* Asterisk is a pointer operator. It is also called a
dereference operator.
Ternary operator: these are the operators that operate on three or more
operands. The symbol ? is used as a ternary operator. the general form of a
ternary operator is,
Expression ? value1 : value2;
Special operators: Java supports two types of operators, instanceof and member
access operator dot(.).
Instanceof operator: this operator returns true if the object belongs to a particular
class.
Example: Hanumanth instanceof employee;
The above statement returns true , because Hanumanth is an object of class,
employee. If not then it returns false .
Dot operator: the dot (.) operator is used to access methods of class and also
used to access the instance variables and to access sub packages from the
packages.
Example: Hanumanth.empno /* accessing instance variable empno
Using the object called Hanumanth */
Hanumanth.compute_salary() /* accessing the method compute_salary() using the object
called Hanumanth */
Precedence or hierarchy of operators:
The order in which the expression is evaluated is called precedence of the
operator. It is also called the priority of operators.

For	example,	2-3*4	can	be	evaluate	the	value	-10,	not	-4	because,	the	operator	*
has	high	priority	than	–	hence,	first	3*4	will	be	evaluated	and	then	subtracted
from	2.	Hence,	we	get	the	result	of	this	expression	as	-10.
The	following	table	shows	the	priority	and	associativity	of	the	operators:
| Operator	category   | Operators          | Precedence | Associativity |
| ------------------- | ------------------ | ---------- | ------------- |
| Parentheses,	braces | (),	[]             | 1          | L	to	R        |
| Unary	operators     | -,	++,	--,	!,	~,	& | 2          | R	to	L        |
| Multiplicative      | *,	/,	%            | 3          | L	to	R        |
operators
| Additive	operators    | +,	-               | 4   | L	to	R |
| --------------------- | ------------------ | --- | ------ |
| Shift	operators       | <<,	>>             | 5   | L	to	R |
| Relational	operators  | <,	<=,	>,	>=       | 6   | L	to	R |
| Equality	operators    | ==,	!=             | 7   | L	to	R |
| Bitwise	operators     | &,	^,	|            | 8   | L	to	R |
| Logical	operators     | &&,	||             | 9   | L	to	R |
| Conditional	operators | ?,	:               | 10  | R	to	L |
| Assignment	operators  | =,	+=,	-=,	*=,	/=, | 11  | R	to	L |
%=,	^=,	|=,	<<=,
>>=
| Comma	operator | ,   | 12  | L	to	R |
| -------------- | --- | --- | ------ |
Mathematical	functions:	mathematical	functions	such	as,	sin,	cos,	log,	sqrt,	pow
etc	are	the	commonly	used	functions	in	a	day	to	day	life.	Java	supports	these
functions	by	using	Math	class	present	in	java.lang	package.	The	following	table
shows	some	of	the	mathematical	functions	present	in	the	Math	class.	each
function	can	be	accessed	as,
Math.function_name();
Example:	double	a=Math.abs(-10.0);
| Function | Meaning |     |     |
| -------- | ------- | --- | --- |
sin(x) Sine	of	an	angle	x
cos(x) Cosine	of	an	angle	x
exp(x) Exponential	function	of	x

log(x) Logarithm of x
log10(x) Logarithm of number x to the base 10
sqrt(x) Square root of x
pow(x,y) x raised to the power y
abs(x) Absolute value of integer number x
ceil(x) Rounding up to the next whole number
floor(x) Rounding down to the next whole number
// Write a program to demonstrate some of the mathematical functions.
class MathDemo
public static void main(String []args)
double x;
x=Math.sin(30);
System.out.println(“The sin of 30 is”+x);
x=Math.sqrt(4);
System.out.println(“The square root of 4 is”+x);
x=Math.abs(-19);
System.out.println(“The absolute value of -19 is”+x);
x=Math.floor(11.4);
System.out.println(“The rounded down number 11.4 is”+x);
x=Math.ceil(14.5);
System.out.println(“The rounded up number 14.5 is”+x);
F:\java programs>javac MathDemo.java
F:\java programs>java MathDemo
The sin of 30 is-0.9880316240928618

The square root of 4 is2.0
The absolute value of -19 is19.0
The rounded down number 11.4 is11.0
The rounded up number 14.5 is15.0
EXERCISES
1. What is operator? explain the types of operators.
2. Explain arithmetic operators with an example for each.
3. Describe relational operators with a programming example.
4. Explain logical operators with the example using the truth table.
5. Explain shorthand assignment operators.
6. Explain increment and decrement operators.
7. What are conditional operators? explain with an example.
8. Explain bitwise operators with an example.
9. What are the special operators used in java? Explain them.
10. How to evaluate an arithmetic expression? Explain with an example.
11. What is the precedence of operator? give the details of precedence of
operators and their associativity.
12. What is type casting? Mention its types.
13. Define implicit type casting with an example.
14. What is explicit type conversion? Explain with an example.
15. List the uses of any five mathematical functions.

CHAPTER 6
SELECTION AND ITERATIVE
STATEMENTS
Introduction: in general instructions written are executed in a linear fashion
called sequential order. It is not mandatory that all the instructions are to be
executed in the order in which they are written, in some situations, the control
may bifurcate based on the condition, some statements get executed repeatedly a
certain number of times based on the condition. Some statements may jump
from one statement to another based on the condition imposed.
Java provides two types of control statements:
- selection statements
- iterative statements
selection statements: selection statements are also called conditional control
statements used to check the condition, based on the condition, the control
makes decision and branching.
There are four types of selections statements,
i. if-statement
ii. if-else statement
iii. nested statement
iv. switch statement
if statement: if statement is also called a one-way branching statement. In simple
if statements, if the condition is true then the statement or set of statements will
get executed. If the condition is false then the next statements, if any, will get
executed.
The general form of if statement is,
if(condition) OR if(condition)
statement1; {
statement1;
statement2;
…

statement-n
}
Flowchart:

// Write a program to find the largest of two numbers
class Largest
{
public static void main(String args[])
{
int a=5,b=10,large;
large=a;
if(b>large)
large=b;
System.out.println(“Largest number is ”+large);
}
}
Output:

F:\java programs >javac Largest.java
F:\java programs >java Largest
Largest number is 10
// Write a program to find the number of people who are eligible for
marriage or not
class Eligibility
public static void main(String args[])
int i, count=0, count1=0,count2=0;
int age[]={18,25,27,16,20};
for(i=0;i<5;i++)
if(age[i]>=21)
count1+=1;
count+=1; //Total people
count2=count-count1;
System.out.println("Number of people eligible for marriages are "+count1);
System.out.println("Number of people not eligible for marriages are "+count2);
F:\java programs >javac Eligibility.java

F:\java programs >java Eligibility
Number of people eligible for marriages are 2
Number of people not eligible for marriages are 3
if-else statement: if-else statement is also called two-way branching. In an if-
else statement the condition will be checked, if the condition is true then
statement 1 will get executed. Otherwise, else part i.e. statement 2 will get
executed.
The general form of if-else statement is,
if(condition)
statement 1;
else
statement 2;
Flowchart:

/* Write a program to count how many odd and even numbers are present
in the given list of numbers. */
class EvenOdd
{
public static void main(String args[])
{
int number[]={10,13,15,16,20};
int even=0,odd=0;
for(int i=0;i<number.length;i++)
{
if(number[i]%2==0)
{
even+=1;
}
else
{
odd+=1;
}
}
System.out.println(“Even numbers: ”+even);
System.out.println(“Odd numbers: ”+odd);

}
}
Output:
F:\java programs >javac EvenOdd.java
F:\java programs >java EvenOdd
Even numbers: 3
Odd numbers: 2
Nested if statement: one if statement is enclosed within another if statement
such if statements are called nested if statement. There are two formats.
Format I: if-else-if statement: this statement is also called an if-else-if ladder.
There are several alternatives available, out of which we have to choose the
statement that matches with the condition. It is also called multiway branching.
The general form of if-else-if statement is,
if(condition1)
statement1;
else
if(condition2)
statement2;
else
----
else
if(condition-n)
statement-n;
else
default statement;

Flowchart:

First condition 1 is tested. If the condition is true then statement 1 is executed.
Otherwise, condition 2 is tested. If condition 2 is true then statement 2 is
executed. Otherwise, condition 3 is tested and so on. Finally, condition-n is
tested. If it is true, statement-n is executed. If none of the conditions is true then
the default statement is executed.
// Write a program to award the grade based on the percentage of marks.
class Percentage
{
public static void main(String args[])
{
int rollNumber[]={1,2,3,4,5,6};
int per[]={86,75,51,42,38,33};
for(int i=0;i<rollNumber.length;i++)
{
if(per[i]>=85&&per[i]<=100)
System.out.println(rollNumber[i]+ “Distinction”);
else if(per[i]>=60)
System.out.println(rollNumber[i]+ “First Class”);
else if(per[i]>=50)
System.out.println(rollNumber[i]+ “Second Class”);
else if(per[i]>=35)
System.out.println(rollNumber[i]+ “Pass Class”);
else
System.out.println(rollNumber[i]+ “Fail”);
}

}
}
Output:
F:\java programs>javac Percentage.java
F:\java programs>java Percentage
1 Distinction
2 First Class
3 Second Class
4 Pass Class
5 Pass Class
6 Fail
Format II: If-else-if statement: it contains an if-else statement within another
if-else statement.
The general form of if-else-if statement is,
if(condition1)
if(condition2)
statement1;
else
statement2
else
if(condition3)
statement3;
else
statement4;
Flowchart:

// Write a program to find the greatest of three numbers
class Greatest
{
public static void main(String args[])
{
int a=10,b=20,c=30;
if(a>b)
{
if(a>c)
{
System.out.println(“Greatest is ”+a);
}
else
{
System.out.println(“Greatest is ”+a);
}

}
else
{
if(c>b)
{
System.out.println(“Greatest is ”+c);
}
else
{
System.out.println(“Greatest is ”+b);
}
}
}
}
Output:
F:\java programs>javac Greatest.java
F:\java programs>java Greatest
Greatest is 30
switch statement: it is an alternative of if-else-if ladder. It is used to select one
block of code if the expression present in the switch statement matches with the
case label. Here switch expression will hold only one of the three values, int,
char or Boolean values. This statement provides multiple way branching.
The general form of switch statement is,
switch(expression)
{
case label-1: statement 1;
break;
case label-2: statement 2;

break;
…
case label-n: statement n;
break;
default: default-statement;
}
The break statement is used to indicate that the case label is terminated. The
default statement will get executed if the switch expression does not match with
any case label.
Flowchart:
// Write a program to display rainbow color using switch statement
class Rainbow
{
public static void main(String args[])
{
int choice=6;
switch(choice)
{

case 1: System.out.println(“Violet”);
break;
case 2: System.out.println(“Indigo”);
break;
case 3: System.out.println(“Blue”);
break;
case 4: System.out.println(“Green”);
break;
case 5: System.out.println(“Yellow”);
break;
case 6: System.out.println(“Orange”);
break;
case 7: System.out.println(“Red”);
break;
default: System.out.println(“Error in color code”);
}
}
}
Output:
F:\java programs>javac Rainbow.java
F:\java programs>java Rainbow
Orange
Iterative statements: iterative statements that allow repeated execution of a set
of instructions till a certain condition is satisfied. Iterative statements are also
called looping statements. There are three types of looping statements.
- while loop
- do-while loop
- for loop
while loop: it is also called a pretested looping structure, because the condition is

checked before repeated execution of a set of statements.
General form,
while(condition) OR while(condition)
statement; {
statement 1;
statement 2;
…
statement n;
}
Flowchart:
// Write a program to print numbers in
ascending order
class Natural
{
public static void main(String args[])

{
int i=1;
while(i<=10)
{
System.out.print(i+”,”);
i++;
}
System.out.print(“\b.”);
}
}
Output:
F:\java programs>javac Natural.java
F:\java programs>java Natural
1,2,3,4,5,6,7,8,9,10.
do-while loop: in this looping structure the condition will be checked executing a
set of instructions at least once, hence it is also called a post-tested looping
structure.
General form of do-while loop is,
do do
{ {
Statement1; OR statement 1;
}while(condition); statement 2;
…
Statement n;
}while(condition);

Flowchart:
OR

// Write a program to print even numbers
class Even
{
public static void main(String args[])
{
int i=2;
do
{
System.out.print(i+”,”);
i=i+2;
} while(i<=10);
System.out.print(“\b.”);
}
}
Output:
F:\java programs>javac Even.java
F:\java programs>java Even
2,4,6,8,10.
for loop: this looping statement is used when there are a fixed number of
iterations. When we know in advance exactly how many times a set of
statements should be repeatedly executed.
The general form of for loop is,
for(initialization; condition; increment/decrement)
{
Statement1;
Statement2;
…
Statement-n;
}

Flowchart:

Where, IV is initial value and FV is final value
// Write a program to find the sum of n natural numbers
class Sum
{
public static void main(String args[])
{
int i, sum=0;
for(i=1;i<=10;i++)
sum=sum+i;
System.out.println(sum);
}
}
Output:
F:\java programs>javac Sum.java
F:\java programs>java Sum
55
Nesting of for loops: one for loop is within another for loop is called nesting of
for loops.
// Write a program to demonstrate the use of nested for loops.
class Nest
{
public static void main(String args[])
{
for(int i=1;i<=9;i++)
{
for(int j=1;j<=i;j++)

{
System.out.print(“ “);
System.out.print(i);
}
System.out.println();
}
}
}
Output:
F:\java programs>javac Nest.java
F:\java programs>java Nest
1
2 2
3 3 3
4 4 4 4
5 5 5 5 5
6 6 6 6 6 6
7 7 7 7 7 7 7
8 8 8 8 8 8 8 8
9 9 9 9 9 9 9 9 9
Note: In the nested for loops, the inner for loop will get executed first and then
the outer for loop will execute.
Jump statements: jump statements are used to terminate or exit or transfer the
control out of the loop, this can be done by using break , exit and continue
statements.
break statement: break statement can be used in two ways; one we have already
used in switch statements to transfer the control out of switch statements.
Another is used to terminate the loop.

General form of break statement is,
break;
continue statement: continue statement is used to skip the part of a looping
statement and does not terminate the loop.
The general form of continue statement is,
continue;
Labelled loops: we can provide a label to a block of statements. To give a label
to a loop, place it before the loop with a colon at the end.
/* Write a program to demonstrate the use of continue and break
statements. */
class ContinueBreakDemo
{
public static void main(String args[])
{
Loop:for(int i=1;i<100;i++)
{
System.out.println(" ");
if(i>=10)
break;
for(int j=1;j<100;j++)
{
System.out.print("*");
if(j==i)
continue Loop;
}
}
}
}
Output:

F:\java programs>javac ContinueBreakDemo.java
F:\java programs>java ContinueBreakDemo
*
**
***
****
*****
******
*******
********
*********
EXERCISES
1. Explain a simple if statement with an example.
2. Describe if else statement with syntax, flowchart and an example.
3. What is a nested loop? Explain with a programming example.
4. Define if-else-if ladder. Write a program to demonstrate if-else-if
ladder.
5. Define switch statement. Explain with a programming example.
6. Explain while statement with general form, flowchart and an example.
7. What is a do-while statement? Explain with an example.
8. Explain the for loop with an example.
9. What is nested for loop? Explain with a programming example.
10. Explain jumps in loops.

CHAPTER 7
CLASS
Introduction: Java is a pure object oriented programming language and
everything represented in a java program must be encapsulated in a class. Once a
class is created, any number of objects of that class type can be created. Objects
use methods to communicate between them. In java data items are called fields ,
the functions are called methods and objects are called instances .
The general form of defining a class:
class class_name
{
Data_type1 variable1;
Data_type2 variable2;
…
Data_type-n variable-n;
Return_type method_name(parameter_list)
{
Method body;
}
}
Unlike C++, methods in java must be completely implemented within the class.
we will not define a method outside the class definition using scope resolution
operator.
Example:
class Rectangle
{
int l,w;
void getdata(int x,int y)
{

l=x;
w=y;
}
int rectArea()
{
int a=l*w;
return a;
}
}
Creating objects: in order to create an object we first declare a variable of class
type and then we use the operator new to create the object and return a reference
to the object.
Rectangle r=new Rectangle();
Or
Rectangle r;
r=new Rectangle();
Now, let us write a complete executable program,
class Rectangle
{
int l,w;
void getdata(int x,int y)
{
l=x;
w=y;
}
int rectArea()
{
int a=l*w;

return a;
}
}
class RectangleArea
{
public static void main(String args[])
{
Rectangle r=new Rectangle();
r.getdata(5,10);
int area=r.rectArea();
System.out.println("Area of a Rectangle "+area);
}
}
Output:
F:\java programs>javac RectangleArea.java
F:\java programs>java RectangleArea
Area of a Rectangle 50
Scope of variables: java variables are classified into three kinds.
- Instance variables
- Class variables
- Local variables
Instance variables: the variables l and w in the program above, are called
instance variables because each time a new object is created a new copy of these
objects will be created. Each object will have its own set of values for these
variables. To access instance variables of an object from the outside class, we
must use the concerned object and the dot (.) operator. instance and class
variables are declared inside a class. instance variables are created when the
objects are instantiated and therefore, they are associated with the objects on the
other hand, class variables are global to a class and belong to the entire set of

objects that class creates; only one memory location is created for each class
variable.
Local variables: variables that are declared can be used within the method
definition are called local variables. Local variables can also be declared inside
program blocks that are defined between an opening and closing braces.
Instance methods: the methods in the above program, getdata() and rectArea()
are called instance methods. They are also accessible by a particular object using
the dot operator.
Example, r.getdata();
r.areaRect();
Constructors: A constructor is a special method used to initialize instance
variables of the class as soon as the object of the class is created. The constructor
method name is the same as that of the class name.
The above program to find the area of a rectangle can be rewritten using
constructor as,
class Rectangle
{
int l,w;
Rectangle(int x,int y) // Constructor with two arguments
{
l=x;
w=y;
}
int rectArea()
{
int a=l*w;
return a;
}
}
class RectangleArea

{
public static void main(String args[])
{
Rectangle r=new Rectangle(5,10);
int area=r.rectArea();
System.out.println(“Area of a Rectangle=”+area);
}
}
Output:
F:\java programs>javac RectangleArea.java
F:\java programs>java RectangleArea
Area of a Rectangle 50
Overloaded methods: Two or more methods having the same name with
different return type or number of arguments are called method overloading.
Method overloading is used when objects are required to perform similar tasks
using different parameter lists. When a program calls a method in an object, java
matches up the method name first and then the number of parameters and their
types, to decide which one of the definitions to execute. This process of selecting
a particular method is called polymorphism .
// Write a program to demonstrate the use of method overloading.
class Box
{
int l;
int w;
Box(int a,int b) // Constructor to compute area of a Rectangular Box
{
l=a;
w=b;

}
Box(int x) // Constructor to compute area of a Square Box
{
l=w=x;
}
int area()
{
return(l*w);
}
}
class BoxArea
{
public static void main(String args[])
{
Box b1=new Box(10,20);
Box b2=new Box(5);
int area1=b1.area();
int area2=b2.area();
System.out.println("Area of a Rectangular box="+area1);
System.out.println("Area of a square box="+area2);
}
}
Output:
F:\java programs>javac BoxArea.java
F:\java programs>java BoxArea
Area of a Rectangular box=200
Area of a square box=25

this: this keyword is a special reference in java, used inside a method to refer to
an invoking object if we use the same name for the parameters of the method
and the instance variables of the class. In this situation, the keyword this is used
to distinguish between the two.
// Write a program to illustrate the use of this keyword
class Rectangle
{
int l,w;
Rectangle(int l,int w) // Constructor with same two arguments
{
this.l=l;
this.w=w;
}
int rectArea()
{
int a=l*w;
return a;
}
}
class RectangleArea
{
public static void main(String args[])
{
Rectangle r=new Rectangle(5,10);
int area=r.rectArea();
System.out.println(“Area of a Rectangle=”+area);
}
}
Output:

F:\java programs>javac RectangleArea.java
F:\java programs>java RectangleArea
Area of a Rectangle=50
One more use of this is, it can be used in one constructor to call another
constructor of the class.
/* Write a program to demonstrate the use of this keyword in one
constructor to call another constructor of the class. */
class Point
{
int a,b;
Point(int a, int b)
{
this.a=a;
this.b=b;
}
Point()
{
this(5,5);
}
float distance(int a, int b)
{
int dx=this.a-a;
int dy=this.b-b;
return(float)Math.sqrt(dx*dx+dy*dy);
}
float distance(Point p)
{

float d=distance(p.a,p.b);
return d;
}
}
class PointDistance
{
public static void main(String args[])
{
Point p1=new Point();
Point p2=new Point(5,6);
float d1=p1.distance(p2);
float d2=p1.distance(5,10);
System.out.println("Distance between p1 and p2 is "+d1);
System.out.println("Distance between p1 and (5,10) is "+d2);
}
}
Output:
F:\java programs>javac PointDistance.java
F:\java programs>java PointDistance
Distance between p1 and p2 is 1.0
Distance between p1 and (5,10) is 5.0
Static members: we know that a class contains instance variables and instance
methods. Every time a class is instantiated, a new copy of each of them is
created. They are accessed using the objects with dot operators.
When we want to define a member that is common to all the objects and
accessed without using a particular object. i.e. the member belongs to the class
as a whole rather than the objects created from the class. static variables and
static methods can be called without using the objects. Java class libraries

contain a large number of class methods (static methods). For example, the Math
class defines many static methods.
Example, float a=Math.sqrt(16.0);
The method sqrt is a class method or static method defined in Math class. we can
also define our own static methods as shown below.
class Max
{
static int x,y;
static void max(int a, int b)
{
x=a;
y=b;
}
static int big()
{
if(x>y)
return x;
else
return y;
}
}
class MaxNumber
{
public static void main(String args[])
{
Max.max(10,20);
int big=Max.big();

System.out.println("Maximum="+big);
}
}
Output:
F:\java programs>javac MaxNumber.java
F:\java programs>java MaxNumber
Maximum=20
// Write a program to generate a simple calculator using static methods.
class Calc
{
static float add(float x, float y)
{
return(x+y);
}
static float sub(float x, float y)
{
return(x-y);
}
static float mul(float x, float y)
{
return(x*y);
}
static float div(float x, float y)
{
return(x/y);
}
}

class Calculator
{
public static void main(String args[])
{
System.out.println("Sum= "+Calc.add(2.0F,3.0F));
System.out.println("Difference= "+Calc.sub(3.0F,4.0F));
System.out.println("Multiplication= "+Calc.mul(4.0F,5.0F));
System.out.println("Sum= "+Calc.div(5.0F,6.0F));
}
}
Output:
F:\java programs>javac Calculator.java
F:\java programs>java Calculator
Sum= 5.0
Difference= -1.0
Multiplication= 20.0
Sum= 0.8333333
Restrictions on static methods:
- One static method can only call another static method in that class.
- Inside the static methods we can only use static variables of a class but not
the instance variable.
- We cannot use this reference inside a static method.
Nesting of methods: Till now we have seen that a method can be called by an
object and class itself in case of static methods. There is another method through
which the method can be accessed is, having the name of the method inside
another method. It is called nesting of methods.
// Write a program to find the sum and average of two subject marks
class SumAvg
{

float science,math;
SumAvg(float a, float b)
{
science=a;
math=b;
}
float compute()
{
float sum=science+math;
float avg=sum/2.0F;
return(avg);
}
void display()
{
System.out.println("Average= "+compute());
}
}
class SumAndAverage
{
public static void main(String args[])
{
SumAvg s=new SumAvg(55,60);
s.display();
}
}
Output:
F:\java programs>javac SumAndAverage.java
F:\java programs>java SumAndAverage

Average= 57.5
Inheritance:
Java classes can be used in several ways. This is done by creating new classes,
reusing the properties of existing one. It allows the user to create a new class
(derived class) from an existing class (base class). The derived class inherits all
the features from a base class and it can have additional features of its own.
Definition: “Inheritance is the capability of one class to inherit properties from
another class”.
Inheritance is a process of acquiring properties from one class to another.
Base class: A class whose properties are inherited to another class is called base
class.
Derived class: A class whose properties are inherited from another class is called
derived class.
Advantages of Inheritance:
- Inheritance permit to reuse already existing code.
- Reduces development time.
- Easy to maintain.
- Easy to extend.
- Memory can be utilized properly.
If we do not use inheritance, then there are two disadvantages,
- Code written in the base class is to be repeated in the derived class that
leads to wastage of memory space.
- Testing to be done separately for both base and derived class that takes a
lot of time.
These problems can be solved using inheritance. If we reuse already existing
code in the base class then the above problems can be tackled.
In the below example, you can find a base class called Employee which inherits
properties to two other derived classes called supervisor and manager that receives
properties from the base class. These classes have properties of base class in
addition they have their own properties.

Levels of inheritance: There are five levels of inheritance. Viz.
- Single inheritance.
- Multilevel inheritance.
- Multiple inheritance.
- Hierarchical inheritance.

Single inheritance: if a class is derived from a single base class is called single
inheritance.
Multilevel inheritance: The classes can also be derived from the classes that are
already derived is called multilevel inheritance.
Multiple inheritance: If a class is derived from more than one base class is

known as multiple inheritance.
Note: java does not directly implement multiple inheritance, however this
concept is implemented using a secondary inheritance path in the form of
interface .
Hierarchical inheritance: If a number of classes are derived from a single base
class is called hierarchical inheritance.
Defining a subclass:

General form of defining a subclass:
class sub_class_name extends super_class_name
{
Variables declaration;
Methods declaration;
}
Here, extends signifies that the properties of the superclass are extended to the
subclass. Subclass contains all the variables and methods of a superclass, in
addition to that subclass has its own variables and methods.
// Write a program to demonstrate single level inheritance.
class Triangle
{
float base, height;
Triangle(float x,float y)
{
base=x;
height=y;
}
float area()
{
return(0.5F*base*height);
}
}
class TrianglularPrismVolume extends Triangle
{
float altitude;
TrianglularPrismVolume(float x, float y, float z)
{

super(x,y);
altitude=z;
}
float volume()
{
return(base*height*altitude);
}
}
class TriangleGeometry
{
public static void main(String args[])
{
TrianglularPrismVolume v=new TrianglularPrismVolume(2.0F,3.0F,4.0F);
float area=v.area();
float volume=v.volume();
System.out.println("Area="+area);
System.out.println("Volume="+volume);
}
}
Output:
F:\java programs>javac TriangleGeometry.java
F:\java programs>java TriangleGeometry
Area=3.0
Volume=24.0
Here, extends is a keyword used to inherit the properties of the base class to
derived class and the keyword super is used to receive the values of the instance
variable from the super class to the derived class.

Method overriding: If a subclass (child class) has the same method as declared
in the parent class, it is known as method overriding in Java . Overridden
methods are used in a situation when we want an object to respond to the same
method but have different behaviour. The method that is defined in the
superclass has the same name, same return type and same number of arguments
in the derived class. when that method is called, the method defined in the
subclass is invoked and executed instead of the one in the superclass. Let us take
an example,
class ParentClass
{
int a;
ParentClass(int x)
{
a=x;
}
void display()
{
System.out.println("ParentClass a="+a);
}
}
class ChildClass extends ParentClass
{
int b;
ChildClass(int x,int y)
{
super(x);
b=y;
}
void display()
{

System.out.println("Parent Class a="+a);
System.out.println("Child Class b="+b);
}
}
class Override
{
public static void main(String args[])
{
ChildClass c=new ChildClass(10,20);
c.display();
}
}
Output:
F:\java programs>javac Override.java
F:\java programs>java Override
Parent Class a=10
Child Class b=20
Advantages of method overriding:
- the class can give its own specific implementation to a inherited method
without even modifying the parent class.
- If a child class needs to use the parent class method, it can use it and the
other classes that want to have different implementations can use the
overriding feature to make changes without touching the parent class code.
Final variables and methods: Unlike C and C++, to make the variable constant
we have used const qualifier. when we use the final keyword in java with the
variables, we are making the variable constant i.e. the variable doesn’t change its
value.
class Value

{
final int x = 10;
void ChangeData()
{
x = 15;
}
public static void main(String args[])
{
Value obj = new Value();
obj.ChangeData();
}
}
Output:
F:\java programs >javac Value.java
Value.java:6: error: cannot assign a value to final variable x
x = 15;
^
1 error
Here, in the ChangeData() method we are trying to change the value of x. but x
is a final variable that does not allow us to change its value. Hence, the compiler
produces an error.
When the final keyword is used with the methods then it prevents method
overriding.
class FinalTest
{
int x;
final void getdata()
{
x=10;
}
}

class FinalDemo extends FinalTest
{
void getdata()
{
x=20;
}
public static void main(String args[])
{
FinalDemo obj=new FinalDemo();
obj.getdata();
}
}
Output:
F:\java programs >javac FinalDemo.java
FinalDemo.java:11: error: getdata() in FinalDemo cannot override getdata() in
FinalTest
void getdata()
^
overridden method is final
1 error
Final classes: final classes prevent from getting inherited.
final class Xyz
{
}
class Abc extends Xyz
{
void getdata()
{

System.out.println(“In sub class method”);
}
public static void main(String args[])
{
Abc obj=new Abc();
obj.getdata();
}
}
Output:
F:\java programs >javac Xyz.java
Xyz.java:4: error: cannot inherit from final Xyz
class Abc extends Xyz
^
1 error
Finalizer methods: we have studied about the dynamic memory allocation for
objects using the new operator, once program execution terminates, the memory
occupied by the object initialised by the constructor has to be deallocated. In C
and C++, we have to deallocate the memory for further allocation using a
destructor. But in java this is done automatically using the built-in system called
Garbage Collection. There is no destructor in java. Java has a better mechanism
to handle the garbage collection. In java the programmer need not to delete
unwanted objects explicitly. JVM does this automatically for you. Even though
the object is deallocated by the Garbage Collector, some other resources such as
file descriptors or window system fonts that are associated with the object
remain occupied. In order to clear them off, we use a method called finalize().
protected void finalize() throws Throwable
{
//Keep some resource closing operations here
}
// Write a program to illustrate the use of finalize() method
class A

{
int i = 10;
protected void finalize() throws Throwable
{
System.out.println("From Finalize Method");
}
}
public class Test
{
public static void main(String[] args)
{
A a1 = new A();
A a2 = new A();
a1 = a2;
System.out.println("Completed");
}
}
Output:
F:\java programs >javac Test.java
F:\java programs >java Test
Completed
Abstract methods and abstract classes: in some situations, we may want to
define a method without any associated code but only the prototype. The code
will be provided in overriding methods of the subclasses such a method in a
superclass is called an abstract method and the class in which such an abstract
method is defined is called an abstract class.
// Write a program to demonstrate abstract class and method.
abstract class sum
{
public abstract int sum(int a, int b);
public void display()
{
System.out.println("Method of class sum");

}
}
class SumTest extends sum
{
public int sum(int a,int b)
{
return(a+b);
}
public static void main(String args[])
{
sum obj=new SumTest();
System.out.println(obj.sum(5,5));
obj.display();
}
}
Output:
F:\java programs>javac SumTest.java
F:\java programs>java SumTest
10
Method of class sum
Note:
- In java, an abstract method must always be written inside an abstract class
only. This does not mean that all the methods in an abstract class should
be abstract.
- We can declare a reference variable of the abstract class but we cannot
create an object of an abstract class.
- The reference variable of an abstract class can point to any subclass
object. It will then call the appropriate overriding method of that subclass.
This decision is taken at runtime and that is how runtime polymorphism is

achieved in java.
Methods with varargs: A method that takes a variable number of arguments is a
varargs method.
Syntax of varargs :
A variable-length argument is specified by three periods(…). For Example,
public static void function_name(int ... a)
{
// method body
}
This syntax tells the compiler that function_name( ) can be called with zero or
more arguments. As a result, here a is implicitly declared as an array of type
int[].
// Write a program to demonstrate varargs.
class Sample
{
static void display(int ...a)
{
System.out.println("Number of arguments: " + a.length);
for (int i: a)
System.out.print(i + " ");
System.out.println();
}
public static void main(String args[])
{
display(10); // one parameter
display(10, 20, 30); // three parameters
display(); // no parameter
}
}
Output:
F:\java programs >javac Sample.java
F:\java programs >java Sample
Number of arguments: 1
10
Number of arguments: 3

10 20 30
Number of arguments: 0
- The … syntax tells the compiler that varargs has been used and these
arguments should be stored in the array referred to by a .
- The variable a is operated on as an array. In this case, we have defined the
data type of a as int. So it can take only integer values. The number of
arguments can be found out using a.length, the way we find the length of
an array in Java.
Note: A method can have variable length parameters with other parameters too,
but one should ensure that there exists only one varargs parameter that should be
written last in the parameter list of the method declaration.
int nums(int a, float b, double … c)
In this case, the first two arguments are matched with the first two parameters
and the remaining arguments belong to c.
class Sample
{
static void display(String str, int ...a)
{
System.out.println("String: " + str);
System.out.println("Number of arguments is: "+ a.length);
for (int i: a)
System.out.print(i + " ");
System.out.println();
}
public static void main(String args[])
{
display("Hanumanth", 10);
display("Ladwa", 10, 20, 30);
display("HanumanthLadwa");
}
}
Output:
F:\java programs >javac Sample.java
F:\java programs >java Sample
String: Hanumanth
Number of arguments is: 1

10
String: Ladwa
Number of arguments is: 3
10 20 30
String: HanumanthLadwa
Number of arguments is: 0
Dynamic method dispatch or runtime polymorphism: Method overriding is
one of the ways in which Java supports Runtime Polymorphism. Dynamic
method dispatch is the mechanism by which a call to an overridden method is
resolved at run time, rather than compile time.
class Human
{
public void watch()
{
System.out.println(“Humans can watch”);
}
}
class Man extends Human
{
public void watch()
{
System.out.println(“Hanumanth can watch”);
}
}
public class TestHuman
{
public static void main(String args[])
{
Human a=new Human();
Human hanumanth=new Man();
a.watch();

hanumanth.watch();
}
}
Output:
F:\java programs >javac TestHuman.java
F:\java programs >java TestHuman
Humans can watch
Hanumanth can watch
Visibility control: by default all the variables of a class are visible in all the
classes of the current package visibility means that the variable can be accessed
directly as an object of the class and the dot operator. In order to modify the
visibility of the variables, java provides four access modifiers.
- private: private variables can be accessed only by the methods within its
own class and not from anywhere outside the class even in the same
package.
- default : The access level of a default modifier is only within the package.
It cannot be accessed from outside the package. If you do not specify any
access level, it will be the default.
- protected: protected variables can be accessed by all the classes of the
current package as well as subclasses in other packages. However, non
subclasses in other packages cannot access these variables.
- public: variables declared as public will be accessible to all classes in all
packages of the program.
Access within within outside package by outside
Modifier class package subclass only package
Private Yes No No No
Default Yes Yes No No
Protected Yes Yes Yes No
Public Yes Yes Yes Yes
Example:
package A

class One
{
private int a;
int b;
protected int c;
public int d;
}
class Two
{
…a;
…b; ✔
…c; ✔
…d; ✔
}
class Three extends One
{
…a;
…b; ✔
…c; ✔
…d; ✔
}
package B
class Four extends A.One
{
…a;
…b;
…c; ✔
…d; ✔
}

class Five
{
…a;
…b;
…c;
…d; ✔
}
Interfaces: multiple inheritance:
Java does not support multiple inheritance i.e. we cannot derive the class from
more than one superclass.
The following example shows the invalid inheritance.
class A extends B,C
{
…
…
}
Here, in this example class A is inherited by two classes B and C respectively.
But in java multiple inheritance is not possible, instead we can use interface .
However, many applications require the use of multiple inheritance. Java
therefore provides an alternative simpler approach to support this concept which
is known as interface. So even though one class cannot extend more than one
superclass it can implement more than one interface.
Definition: like a class, an interface also contains variables and methods. The
difference is that an interface can contain only final variables and abstract
methods. This means in an interface the data members can only be constants and
all the methods only specify the prototype without a body. The body for these
methods will be written inside the class which implements the interface.
The general form of defining interface is,
interface interface_name
{
static final data_type variable=value;

…
…
return_type method_name(parameter_list);
…
…
}
Example:
interface area
{
final static float PI=3.142F;
float compute();
void show(float s);
}
Implementing an interface:
class C implements I // I is an interface
{
…
…
}
All the constants and methods defined in the interface ‘I’ are accessible within
the class ‘C’.
// Write a program to implement the interface
interface area
{
final static float PI=3.142F;
float compute();
void display(float s);
}
class rectangle implements area

{
float l,w;
rectangle(float x, float y)
{
l=x;
w=y;
}
public float compute()
{
return(l*w);
}
public void display(float s)
{
System.out.println(“Area of rectangle is ”+s);
}
}
class circle implements area
{
float r;
circle(float rad)
{
r=rad;
}
public float compute()
{
return PI*r*r;
}
public void display(float s)
{

System.out.println(“Area of circle is ”+s);
}
}
class Sample
{
public static void main(String args[])
{
rectangle rect=new rectangle(10,20);
circle cir=new circle(10);
float x;
area a=null;
boolean flag=true;
String s1=args[0].toUpperCase();
if(s1.equals(“R”))
a=rect;
else
if(s1.equals(“C”))
a=cir;
else
flag=false;
if(flag)
{
x=a.compute();
a.display(x);
}
else
System.out.println(“Wrong choice”);
}
}

Output:
Run 1:
F:\java programs >javac Sample.java
F:\java programs >java Sample C
Area of circle is 314.2
Run 2:
F:\java programs >java Sample R
Area of rectangle is 200.0
Note:
- as illustrated in the above program accessing different implementations of
an interface through an interface reference variable is how java achieves
runtime polymorphism.
- The prototype methods of an interface which are implemented by a class
must be declared as public . This is because the default access specifier in
an interface is always public and the class which implements it cannot
make the method more restrictive.
- Signature of the methods in the interface and classes must match.
- Since, interfaces are in a different hierarchy from classes. It is possible for
unrelated classes to implement the same interface.
Different options for extending classes and implementing interfaces:
- One class can implement more than one interface.
- A class can extend one superclass and implement one or more interfaces.
1. class C implements I1,I2
{
…
…
}
2. class C extends S implements I1,I2

{
…
…
}
3. One interface can extend another interface
interface I1 extends I2 // super interface
{
…
…
}
Example:
interface ItemConstants
{
int part_code=125;
String part_name=”Table Fan”;
}
interface Item extends ItemConstants
{
void display();
}
The advantage of this approach is that a class which requires only the constants
can implement the interface ItemConstants while another class which may
require the constant as well as the display method can implement only Item.
4. One interface can extend several interface
interface I1 extends I2,I3…
{
…
…
}
For example:
interface ItemMethods
{
void display();
}
interface comb extends ItemConstants, ItemMethods

{
…
…
}
This way several interfaces can be combined into a single interface.
// Write a program to access interface variables.
class Employee
{
int empid;
void getid(int n)
{
empid=n;
}
void putid()
{
System.out.println(“Employee Id=”+empid);
}
}
class Promotion extends Employee
{
float promotion1,promotion2;
void getPromotion(float p1,float p2)
{
promotion1=p1;
promotion2=p2;
}
void putPromotion()
{
System.out.println(“Promotions Received:\n”);
System.out.println(“First Promotion=”+promotion1);

System.out.println(“Second Promotion=”+promotion2);
}
}
interface bonus
{
float bonusAmount=4500.50F;
void putBonus();
}
class Output extends Promotion implements bonus
{
float total;
public void putBonus()
{
System.out.println(“Bonus Amount=”+bonusAmount);
}
void display()
{
total=promotion1+promotion2+bonusAmount;
putid();
putPromotion();
putBonus();
System.out.println(“Total Amount=”+total);
}
}
class Mixture
{
public static void main(String args[])
{
Output employee1=new Output();

employee1.getid(1);
employee1.getPromotion(1500.50F,1200.25F);
employee1.display();
}
}
Output:
F:\java programs >javac Mixture.java
F:\java programs >java Mixture
Employee Id=1
Promotions Received:
First Promotion=1500.5
Second Promotion=1200.25
Bonus Amount=4500.5
Total Amount=7201.25
EXERCISES
1. Define a class. write its syntax.
2. Explain how to do field and method declaration in a class?
3. How to access class members.
4. Define constructor. Explain with a programming example.
5. What is method overloading? Explain with a programming example.
6. Describe static members with an example.
7. What do you mean by nesting methods? Explain with an example.
8. What are overriding methods? Explain with a programming example.
9. What is the use of final variables, methods and classes? explain with
an example for each.
10. What is the use of finalize() method? Explain with the programming
example.

11. Explain methods with varargs with an example.
12. What is dynamic method dispatch? Explain with a programming
example.
13. Define visibility mode. Explain all the visibility modes of java.
14. What is the interface? Write syntax for defining interface.
15. Write a program to demonstrate interface implementation.
16. Write a program to illustrate accessing interface variables.

CHAPTER 8
ARRAYS, STRINGS AND VECTORS
Introduction: Till now, we have seen different types of variables that hold only
one value, but in some situations, we require to process more than one data item
of the same types such as int, char, float or double. In this situation, the concept
of arrays comes into picture.
Definition: “Array is a collection of homogeneous data items under the same
name”.
Array allocates memory in a contiguous way, so that each data item can be easily
accessed with the help of index numbers. Each element is stored in a contiguous
memory location on RAM. The index numbers start from 0, 1, 2, …, n-1. These
indices are also called subscripts of an array. These indices are used to locate the
positions of elements within the array.
Let us consider, if a is the name of the array, the elements can be accessed as
a[0], a[1], a[2], … ,a[n-1]. a[0] is the name of the first element present at
position 0, a[1] is the name of the second element present at position 1 and so
on. a[i] is the element present at position i.
10 20 30 40 50
An array is mapped in a memory as,
a
0 1 2 3 4
Here, a is the name of the array, a[0] contains the element 10, a[1] contains the
element 20, a[2] contains the element 30, a[3] contains the element 40, a[4]
contains the element 50. The subscripts always start from 0 to n-1.
Types of arrays:
- one-dimensional array
- two-dimensional array
- multidimensional array
one-dimensional array: an array in which each element is accessed by only one
subscript is called a one-dimensional array.
Declaration:

type arrayname[];
or
type [] arrayname;
Example:
int a[]; or int [] a;
float b[]; or float [] b;
Creating arrays: We need to allow arrays to allocate memory location for the
declaration using a new operator.
arrayname =new type[size];
int a[]=new int[5];
Initialization of arrays: one-dimensional arrays can be initialized as,
arrayname[subscript]=value;
Example:
a[0]=10;
a[1]=20;
…
a[4]=50;
Arrays can be initialized automatically like normal variables as shown below,
type arrayname[]={list of values};
Example:
int a[]={10,20,30,40,50};
Note:
- No size is given. Hence, the compiler allocates enough space for all the
elements specified in the list.
- In java, there is a strict bound checking. Means, the number of elements
must be equal to the size given in the array.
Array Length: the allocated size of the array is stored in the variable called
length. The total number of elements can be determined by using a.length.
Example: int l=a.length; /* total number of elements of array a stores in

variable l. */
// Write a program to sort the elements using length variables.
class Sort
{
public static void main(String args[])
{
int a[]={30,10,50,20,40};
int l=a.length;
System.out.print("Given list of elements are");
for(int i=0;i<l;i++)
{
System.out.print(" "+a[i]);
}
System.out.println("");
for(int i=0;i<l;i++)
{
for(int j=i+1;j<l;j++)
{
if(a[i]>a[j])
{
int temp;
temp=a[i];
a[i]=a[j];
a[j]=temp;
}
}
}

System.out.println("Sorted list is");
for(int i=0;i<l;i++)
{
System.out.print(" "+a[i]);
}
}
}
Output:
F:\java programs>javac Sort.java
F:\java programs>java Sort
Given list of elements are 30 10 50 20 40
Sorted list is
10 20 30 40 50
Two dimensional arrays: an array in which each element can be accessed with
the help of two subscripts is called a two-dimensional array.
Two-dimensional array consists of rows and columns that forms a matrix shown
below,

Declaration:
int a[][];
a=new int[2][3];
or
int a[][]=new int [2][3];
Initialization of tow dimensional arrays: two-dimensional arrays can be
initialized as,
int a[2][3]={1,2,3,4,5,6};
This statement initializes the first row with the first three numbers 1,2 and 3 and
then second row with the elements 4,5 and 6. The above statement can also be
written as,
int a[2][3]={{1,2,3},{4,5,6}};
we can also initialize two dimensional arrays in the form of a matrix as shown
below,
int a[2][3]={
{1,2,3},
{4,5,6}
};
/* Write a program to print the multiplication table using a two dimensional
array. */
class Table
{
public static void main(String args[])
{
int product[][]=new int[11][11];
int i,j;
System.out.println("MULTIPLICATION TABLE");

for(i=1;i<=10;i++)
{
for(j=1;j<=5;j++)
{
product[i][j]=i*j;
System.out.print(j+"*"+i+"="+product[i][j]+"\t\t");
}
System.out.println("");
}
}
}
F:\java programs>javac Table.java
F:\java programs>java Table
MULTIPLICATION TABLE
1*1=1 2*1=2 3*1=3 4*1=4 5*1=5
1*2=2 2*2=4 3*2=6 4*2=8 5*2=10
1*3=3 2*3=6 3*3=9 4*3=12 5*3=15
1*4=4 2*4=8 3*4=12 4*4=16 5*4=20
1*5=5 2*5=10 3*5=15 4*5=20 5*5=25
1*6=6 2*6=12 3*6=18 4*6=24 5*6=30
1*7=7 2*7=14 3*7=21 4*7=28 5*7=35
1*8=8 2*8=16 3*8=24 4*8=32 5*8=40
1*9=9 2*9=18 3*9=27 4*9=36 5*9=45
1*10=10 2*10=20 3*10=30 4*10=40 5*10=50
Variable size arrays: java supports multidimensional arrays and treats it as an
array of arrays. It is possible to declare two dimensional array in variable size as
shown below,
int a[][]=new int [2][];

a[0]=new int[2];
a[1]=new int [4];
a[2]=new int[3];
These statements create a two dimensional array with different column numbers
as shown in the following figure.
Strings: string is a sequence of characters enclosed within a pair of double
quotation marks. It is used to represent a sequence of characters by using a
character array.
Example:
char ch[]=new char[4];
ch[0]=’J’;
ch[1]=’a’;
ch[2]=’v’;
ch[3]=’a’;
In Java, strings are class objects and implemented using two classes, String and
StringBuffer. Java strings are more reliable and predictable due to C’s lack of
bound checking. A java string is not an array of characters and they are not
terminated by NULL.
The general form for declaring string is,
String string_name;
string_name=new String(“string”);

or
String string_name=new String(“string”);
Example:
String firstName;
firstName=new String(“Hanumanth”);
or
String firstName=new String(“Hanumanth”);
Java strings can be concatenated using + operator.
Example:
String firstName=new String(“Hanumanth”);
String lastName=new String(“Ladwa”);
String fullName=firstName+lastName;
String arrays: we can create and use arrays that contain strings is shown below,
String names[]=new String[3];
This statement holds 3 names.
String Methods: If we want to perform some string manipulation operations
then built-in functions present in the String class can be used. The following
table lists the functions present in the String class.
s1.chartAt(n) Returns the character at the specified
index.
s1.compareTo(s2) Compares two strings if s1>s2
returns 1, if s1<s2 returns -1 and s1
is equal to s2 returns 0.
s1.compareToIgnoreCase(s2) Returns true if s1=s2, ignoring case
of characters
s2=s1.toLowerCase Converts s1 into lowercase.
s2=s1.toUpperCase Converts s1 into uppercase.
s2=s1.replace(‘x’,’y’); Replace all appearances of x with y
s2=s1.trim(); Remove blank spaces at the
beginning and end of the string s1.
s1.equals(s2) Returns true if s1 equals s2,

otherwise returns false.
s1.equalIsIgnoreCase(s2) Returns true if s1 equals s2, it is case
insensitive.
s1.length() Gives the length of s1.
s1.concat(s2) Combines both s1 and s2 strings
p.toString() Creates a string representation of the
object p.
s1.indexof(‘x’) Gives the position of the first
occurrence of ‘x’ in the string s1.
s1.indexof(‘x’,n) Gives the position of ‘x’ that occurs
after nth position in the string s1.
/* Write a program to use some of the built-in string functions to sort
strings. */
class StringSort
{
static String name[]=
{“Priyanka”,”Sneha”,”Sonia”,”Swaroop”,”Raj”,”Rakshita”};
public static void main(String args[])
{
int l=name.length;
String temp=null;
for(int i=0;i<l;i++)
{
for(int j=i+1;j<l;j++)
{
if(name[j].compareTo(name[i])<0)
{
temp=name[i];
name[i]=name[j];
name[j]=temp;
}

}
}
for(int i=0;i<l;i++)
{
System.out.println(name[i]);
}
}
}
Output:
F:\java programs >javac StringSort.java
F:\java programs >java StringSort
Priyanka
Raj
Rakshita
Sneha
Sonia
Swaroop
StringBuffer class: StringBuffer class creates strings of flexible length. With the
help of this class, we can insert characters and substrings in the middle of a
string, or append another string to the end. List of some of the StringBuffer class
methods is given below.
Method Description
s1.setCharAt(n,’x’) Modifies the nth character to x.
s1.append(s2) s2 string joins at the end of s1 string.
s1.insert(n,s2) Inserts the string s2 at the position n
of the string s1.
s1.setLength(n) Sets the length of the string s1 to n.
if n<s1.length() s1 is truncated. If
n>s1.length() zeros are added to s1.
// Write a program to use some of the built-in StringBuffer methods.

class StringBufferMethods
{
public static void main(String args[])
{
StringBuffer str=new StringBuffer("We one");
System.out.println("Original String: "+str);
System.out.println("The length of the string: "+str.length());
for(int i=0;i<str.length();i++)
{
int p=i+1;
System.out.println("Character at position: "+p+" is: "+str.charAt(i));
}
String aString=new String(str.toString());
int pos=aString.indexOf(" one");
str.insert(2," all are");
System.out.println("Modified string: "+str);
str.append(" in every way. ");
System.out.println("Append string "+str);
}
}
Output:
F:\java programs>javac StringBufferMethods.java
F:\java programs>java StringBufferMethods
Original String: We one
The length of the string: 6
Character at position: 1 is: W
Character at position: 2 is: e
Character at position: 3 is:

Character at position: 4 is: o
Character at position: 5 is: n
Character at position: 6 is: e
Modified string: We all are one
Append string We all are one in every way.
Vectors: Vector class can be used to create generic dynamic arrays. This class is
present in the java.util package. This class can hold objects of any type and any
number. Vectors are created like arrays as shown below.
Vector vect=new Vector(); // Declaring without size
Vector vect=new Vector(5); // Declaring with size
Note:
- A vector can be declared without specifying any size.
- A vector can have an unknown number of elements even though the size
is specified.
Advantages of vectors:
- Storing objects is convenient.
- Variable size of objects can be stored.
- Addition and deletion of an element is easy.
Disadvantages of vectors:
- Cannot store simple data types.
- It can store only objects.
- Need to convert simple types to objects.
Some of the methods of vectors are listed in the table below.
Method call Description
list.addElement(item) Adds the items specified to the list at
the end.
list.elementAt(5) Gives the name of the 5 th object.
list.size() Returns the number of objects
present.
list.removeElement(item) Removes the specified item from the
list.
list.removeElementAt(n) Removes the items stored in the nth

position of the list.
list.removeAllElements() Removes all the elements in the list.
list.copy Into(array) Copies all items from list to array
list.insertElementAt(item,n) Inserts the item at the nth position.
// Write a program to demonstrate some of the methods of Vector class.
import java.util.*;
class VectorExample
{
public static void main(String args[])
{
Vector list=new Vector();
int l=args.length;
for(int i=0;i<l;i++)
{
list.addElement(args[i]);
}
list.insertElementAt(“Priyanka”,1);
int size=list.size();
String listArray[]=new String[size];
list.copyInto(listArray);
System.out,.println(“List of family members”);
for(int i=0;i<size;i++)
{
System.out.println(listArray[i]);
}
}
}
Output: java VectorExample Sneha Sonia Swaroop Raj Rakshita
Wrapper classes: we have studied that the vectors cannot handle primitive data
types (int, char, float, long and double), these may be converted into object types

by using wrapper classes present in the java.lang package. The following table
shows the primitive data types and their corresponding wrapper class types.
Primitive type Wrapper class
boolean Boolean
char Character
double Double
float Float
int Integer
long Long
char Char
public class Main
{
public static void main(String[] args)
{
Integer IntVal = 10;
Double DoubleVal = 12.23;
Character CharVal = 'H';
System.out.println(IntVal);
System.out.println(DoubleVal);
System.out.println(CharVal);
}
}
Output:
F:\java programs >javac Main.java
F:\java programs >java Main
10
12.23
H
The wrapper classes have various methods for handling primitive data types and

objects. All of these are listed in the following table:
Converting primitive data types into object type using constructor methods.
Constructor calling Description
Integer IntVal=new Integer(i); Primitive integer to Integer object.
Float FloatVal=new Float(f); Primitive float to Float object.
Long LongVal=new Long(l); Primitive long to Long object.
Double DoubleVal=new Double(d); Primitive double to Double object.
Here, i, f, l, d are primitive data values representing int, float, long and double
respectively. They may be variables or constants.
Converting object numbers to primitive numbers using typeValue() method.
Method Description
int i=IntVal.intValue(); Object to primitive integer.
float f=FloatVal.floatValue(); Object to primitive float.
long l=LongVal.longValue(); Object to primitive long.
double d=DoubleVal.doubleValue(); Object to primitive double.
char c=CharVal.charValue(); Object to primitive char.
public class Main
{
public static void main(String[] args)
{
Integer IntVal = 10;
Double DoubleVal = 15.24;
Character CharVal = 'H';
System.out.println(IntVal.intValue());
System.out.println(DoubleVal.doubleValue());
System.out.println(CharVal.charValue());
}
}
Output:
F:\java programs >javac Main.java

F:\java programs >java Main
10
15.24
H
Converting numbers to strings using the toString() method.
Method Description
str=Integer.toString(i); Primitive integer to string.
str=Float.toString(f); Primitive float to string.
str=Long.toString(l); Primitive long to string.
str=Double.toString(d); Primitive double to string.
public class Main
{
public static void main(String[] args)
{
Integer IntVal = 400;
String StringVal = IntVal.toString();
System.out.println(StringVal.length());
}
}
Output:
F:\java programs >javac Main.java
F:\java programs >java Main
3
Converting string objects to numeric objects using the static method valueOf()
method.
Method Description
IntVal=Integer.valueOf(str); Converts string to Integer object.
FloatVal=Float.valueOf(str); Converts string to Float object.

LongVal=Long.valueOf(str); Converts string to Long object.
DoubleVal=Double.valueOf(str); Converts string to Double object.
Note: these numeric objects may be converted to primitive numbers using
parsing methods.
Method Description
int i=Integer.parseInt(str); Converts string to primitive integer.
long i=Long.parseLong(str); Converts string to primitive long.
Note: parseInt() and parseLong() methods throw a NumberFormatException if
the value of the str does not represent an integer.
Autoboxing and unboxing: Converting a primitive value into an object of the
corresponding wrapper class is called autoboxing. For example, converting int
to Integer class . The Java compiler applies autoboxing when a primitive value
is:
- Passed as a parameter to a method that expects an object of the
corresponding wrapper class.
- Assigned to a variable of the corresponding wrapper class .
Example: int a =10;
Integer b=a;
Converting an object of a wrapper type to its corresponding primitive value is
called unboxing. For example conversion of Integer to int. The Java compiler
applies unboxing when an object of a wrapper class is:
- Passed as a parameter to a method that expects a value of the
corresponding primitive type.
- Assigned to a variable of the corresponding primitive type .
Example: Integer a=10;
int b=a;
class AutoBoxAndUnBox
{
public static void main (String[] args)
{
int a = 10;
Integer b = a; // Autoboxing
int c=b; // Unboxing

System.out.println("Value of a: " + a);
System.out.println("Value of b: " + b);
System.out.println("Value of c: " + c);
}
}
Output:
F:\java programs >javac AutoBoxAndUnBox.java
F:\java programs >java AutoBoxAndUnBox
Value of a: 10
Value of b: 10
Value of c: 10
Enumerated types: it is an user defined data type consisting of a set of named
constants called enumerators. enum is a keyword that assigns values 0, 1, 2, …
automatically.
The general form of enum is as follows:
enum[tag]{enum-list};
Example 1: enum ratings{very_bad,bad,good,better,best}; // definition of enum
type
ratings myrate; //declaration of variable of type ratings
// Write a program to demonstrate the use of enumerated data types.
public class Main
{
enum ratings{very_bad,bad,good,better,best};
public static void main(String args[])
{
ratings myrate=ratings.best;
System.out.println(myrate);
}
}

Output:
F:\java programs >javac Main.java
F:\java programs >java Main
best
Annotations: Annotations are tags attached with class, interface, methods or
fields to indicate some additional information that can be used by java compiler
and JVM. The tag represents the metadata.
Built-In Java Annotations used in Java code
- @Override
- @SuppressWarnings
- @Deprecated
Built-In Java Annotations used in other annotations
- @Target
- @Retention
- @Inherited
- @Documented
@Override:
@Override annotation assures that the subclass method is overriding the parent
class method. If it is not so, a compile time error occurs.
If there is a spelling mistake in the overridden function then it is better to mark
@Override. This assures that the method is overridden.
class Human
{
void eatHelathy()
{
System.out.println(“Eat Healthy”);
}
}
class Man extends Human
{
@Override

void eathealhty() // here is a mistake this should be eatHealthy()
{
System.out.println(“Eating Good Food”);
}
}
class Food
{
public static void main(String args[])
{
Human h=new Man();
h.eatHealthy();
}
}
This program produces a compile time error as shown below.
F:\java programs>javac Food.java
Food.java:10: error: incompatible types: Override cannot be converted to
Annotation
@Override
^
Food.java:21: error: cannot find symbol
h.eatHealthy();
^
symbol: method eatHealthy()
location: variable h of type Human
2 errors
@SuppressWarnings: is used to suppress warnings issued by the compiler.
import java.util.*;
class names
{
@SuppressWarnings("unchecked")
public static void main(String args[])
{
ArrayList list=new ArrayList();
list.add("Raj");

list.add("Rakshita");
for(Object obj:list)
System.out.println(obj);
}
}
Output:
F:\java programs >javac names.java
F:\java programs >java names
Raj
Rakshita
If we remove the @SuppressWarnings("unchecked") annotation, it will show a
warning at compile time because we are using a non-generic collection.
@Deprecated:
It marks that this method is deprecated so the compiler prints a warning. It
informs users that it may be removed in the future versions. So, it is better not to
use such methods.
class Name
{
void display()
{
System.out.println("Hello Priya");
}
@Deprecated
void show()
{
System.out.println("Hello Sneha");
}
}

class TestName
{
public static void main(String args[])
{
Name a=new Name();
a.show();
}
}
Output:
F:\java programs >javac TestName.java
F:\java programs >java TestName
Hello Sneha
@Target: This tag is used to specify at which type, the annotation is used.
The java.lang.annotation. ElementType enum declares many constants to specify
the type of element where annotation is to be applied such as TYPE, METHOD,
FIELD etc.
Element Types Where the annotation can be applied
TYPE class, interface or enumeration
FIELD fields
METHOD methods
CONSTRUCTOR constructors
LOCAL_VARIABLE local variables
ANNOTATION_TYPE annotation type
PARAMETER parameter
Example to specify annotation for a class
@Target(ElementType.TYPE)
@interface MyAnnotation
{
int value1();

String value2();
}
Example to specify annotation for a class, methods or fields
@Target({ElementType.TYPE, ElementType.FIELD,
ElementType.METHOD})
@interface MyAnnotation
{
int value1();
String value2();
}
@Retention: This annotation is used to specify to what level annotation will be
available.
RetentionPolicy Availability
RetentionPolicy.SOURCE refers to the source code, discarded during
compilation. It will not be available in the
compiled class.
RetentionPolicy.CLASS refers to the .class file, available to java
compilers but not to JVM . It is included in
the class file.
RetentionPolicy.RUNTIME refers to the runtime, available to java
compiler and JVM .
Example to specify the RetentionPolicy
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnnotation
{
int value1();
String value2();
}
Example of custom annotation: creating, applying and accessing annotation
import java.lang.annotation.*;

import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation
{
int value();
}
//Applying annotation
class Hello
{
@MyAnnotation(value=10)
public void sayHello()
{
System.out.println("hello annotation");
}
}
//Accessing annotation
class TestCustomAnnotation1
{
public static void main(String args[])throws Exception
{
Hello h=new Hello();
Method m=h.getClass().getMethod("sayHello");
MyAnnotation manno=m.getAnnotation(MyAnnotation.class);
System.out.println("value is: "+manno.value());
}
}
@Inherited:

By default, annotations are not inherited to subclasses. The @Inherited
annotation marks the annotation to be inherited to subclasses.
@Inherited
@interface ForEveryone { }//Now it will be available to subclass also
@interface ForEveryone { }
class Superclass{}
class Subclass extends Superclass{}
@Documented: This Marks the annotation for inclusion in the documentation.
EXERCISES
1. What is a one dimensional array?
2. How to declare and create a one dimensional array in java?
3. Explain initialization of arrays.
4. Write a program to demonstrate the use of array length.
5. Define a two dimensional array. Explain its initialization.
6. Explain variable size arrays in java.
7. Explain any five string methods.
8. Write a program to sort the names in alphabetical order using some of
the built-in string functions.
9. What is a string buffer class? explain its methods.
10. What are vectors? Write a program to demonstrate the use of vectors.
11. What are wrapper classes? Name them.
12. Define autoboxing and unboxing with a programming example.
13. What is the enumerated type? write a program to illustrate the same.
14. What are annotations? Explain built-in annotations.

CHAPTER 9
JAVA PACKAGES
Introduction: An important feature of object oriented programming is its ability
to reuse code which has already been developed and tested. One way of doing
this is by extending the classes and implementing interfaces. However, this is
limited to reusing the classes and interface within the same program. In order to
make use of those classes and interfaces which are stored in other packages
without physically copying them into a program.
You can import the package containing that class into your program. This
concept is similar to using class libraries in C++. Thus, a package acts as a
container for a set of related classes and interfaces.
Benefits of packages:
- Code reusability.
- Prevents name collision.
- Two classes in a package can have the same name. They are referred to by
their fully qualified names.
packagename.classname
or
packagename.subpackagename.classname
classes can be hidden inside the package so that their data members or functions
or even the class function cannot be accessed directly from outside the function.
classification of packages: packages in java are classified as follows:
- Java API package: API packages are predefined packages that are part of
the Java library and stored in JDK by sun microsystem. All these packages
are available for import into the program. Packages contain hundreds of
classes and interfaces.
- User-defined packages: The programmer can create his/her own
packages. These packages can be used by any other java developer.
Java API packages:
java.lang: this package contains the classes and interfaces used by the compiler.
Hence, this package is automatically imported in every program the user writes.
Some of the classes of this package are, String, Thread, Exception etc.

java.util: this package contains the utility classes for random number generation,
data handling vectors etc.
java.io: it supports both console and file I/O. For example, Data Input Stream,
Data Output Stream, File, Random Access File.
java.awt (Abstract Window Toolkit): it contains classes for implementing GUI
(Graphical User Interface) such as buttons, scroll bars, menus, combo box etc.
java.net: it contains the classes for networking programs. i.e. sending and
receiving information between a client and server.
socket, server socket: it is used for creating and implementing applet programs.
Example: Applet.
Rules for packages:
- The source file must have the same as the name of the public class with
extension (dot) .java.
- The source file should be saved in a subdirectory with the same name as
the package name.
- When no package statement is used all the classes in that source file will
become part of an unnamed default package. Now there is no need for a
public class.
- If a package contains more than one public class then it is split into
several source files.
Accessing user defined packages: accessing user defined packages is possible
by using import statements. The restriction is that the program containing the
import statement must be stored in a directory which is at the higher level, then
the directory in which the package is stored.
All these API packages are organized in hierarchical structure as shown below.

Referring to the public classes of a package: there are two ways of referring to
the public classes of a package.
- Use the fully qualified name of the class.
Example, to create an object of the class Font then,
java.awt.Font f=new java.awt.Font();
this procedure becomes lengthy, if the class has to be referred many times
in the program.
- Import statement:
General form: import packagename.classname;
Example, import java.awt.Font;

Now we can create an object of the class Font as shown below.
Font f=new Font();
If the program uses different classes of the package then you can import
all the classes and interface of the package by writing.
import packagename.*;
Note:
- The import statement must appear at the top of the program outside all the
classes.
- Once a class has been imported its name can be directly used in the
program.
// Write a program to show the current date and time.
import java.util.Date;
class SampleDate
{
public static void main(String args[])
{
Date d=new Date();
System.out.println(“Today is ”+d);
}
}
Output:
F:\java programs >javac SampleDate.java
F:\java programs >java SampleDate
Today is Sat Jul 24 20:18:54 IST 2021
Naming conventions in java:
- Package name: lowercase for first word capital for each subsequent word.
Example, myPackage
- Class name/ interface name: capital letter for starting of each word rest
in lowercase. Example: Math, PointDist, StackTest.
- Method name: similar to package name. example, toLowerCase();
- Variables: all letters should be lowercase and must follow all the rules for
writing identifier names. example, int abc;

- Symbolic names: all letters should be uppercase.
Creating user-defined packages: to create your own package, begin with the
keyword package followed by the name of the package. This must be the first
statement in a java source file. Then we define a class as usual.
package myPack;
public class Balance
{
String name;
double balance;
public Balance(String n, double b)
{
name=n;
balance=b;
}
public void show()
{
if(balance<0)
System.out.println("-->");
System.out.println(name+":$"+balance);
}
}
The above source file should be stored as Balance.java in the directory name
myPack
import myPack.Balance;
class Sample
{
public static void main(String args[])
{
Balance cust=new Balance(“Michael”,1000);
cust.show();

}
}
The above source file should be stored in bin folder as Sample.java
Here the package name is myPack. The class Balance is considered as a part of
this package.
Output:
F:\java programs>javac Sample.java
F:\java programs>java Sample
Michael:$1000.0
Accessing a package: recall that we have already learnt that the package can be
referred either using a fully qualified class name or using import statement. If
there are many references then we use an import statement. The same approach
for accessing packages is even applied to user defined packages as well.
import package1[.package2][.package3].classname;
Here, you observe the hierarchy of directories, i.e., package1 is the name of the
top level package, package2 is inside the package1 and so on. and finally, the
explicit classname is specified. Note that multiple import statements are allowed.
Note:
- Accessing classname using fully qualified name means including only a
particular class required in the program.
- In the case of the import statement method, we import all the classes that
are present in that package.
Adding a class to an existing package: consider the following package,
package pack;
public class A
{
// Body of A
}
The package pack contains class A. we can add another class B to the same
package as
package pack

public class B
{
// Body of B
}
Save both of these two classes A.java and B.java in the directory pack. Once
these classes are compiled, the .class files are created in the same directory. Now
the package pack contains two classes hence we can import both of these two
classes into the program by the following statement.
import pack.*;
Making java classes hidden: all classes defined with public keywords are
imported. If we want some classes not to import into our program then we do not
make them public as shown below.
package pack;
public class A
{
// Body of class A
}
class B
{
// Body of class B
}
Here, the class B which is not declared public is hidden from outside of the
package pack. This class can be seen and used only by the other classes in the
same package.
Note: A java source file should contain only one public class and may include
any number of non-public classes.
Trying to access class B outside the package leads to error.
static import: It eliminates the need of qualifying a static member with the class
name or interface name. And also, the static import feature eliminates the
redundancy of using the qualified class name with the static member name and
increases the readability of the program.
for example: double area=0.33*PI*r*r*h;

Here, PI is a static member of the class, Math.
/* Write a program to demonstrate the use of static members to find the
volume of a cone. */
import static java.lang.Math.*;
public class ConeVolume
{
public void volume(double r, double h)
{
double volume=0.33*PI*r*r*h;
System.out.println("The volume of a cone is: "+volume);
}
public static void main(String args[])
{
ConeVolume cv=new ConeVolume();
cv.volume(3.0,2.0);
}
}
Output:
F:\java programs >javac ConeVolume.java
F:\java programs >java ConeVolume
The volume of a cone is: 18.661060362323372
EXERCISES

1. What is a java package? What are the types of packages?
2. Write the benefits of packages.
3. What are the rules for packages?
4. How to access user defined packages?
5. Explain the ways of referring to the public classes of packages.
6. Write a program to show the current date and time.
7. Explain the naming conventions in java.
8. How do you create user defined packages? Explain with a suitable
example.
9. How to access the package?
10. How to add a class to an existing package?
11. Explain the method of making java classes hidden.
12. What is static import? Explain with a programming example.

CHAPTER 10
PROGRAMMING THREADS
Introduction: all the programs studied so far were single threshed. In all these
programs there was a single path of execution. A multithreaded program on the
other hand is one that is divided into more subprograms which are executed by
the CPU in parallel. Each of these subprograms are called threads . Where each
thread defines a separate path of execution when there is a single CPU in the
computer, in reality it will be executing only one instruction from any one thread
at a time. However, the CPU is able to switch between the threads, so fast that it
appears that they are being executed simultaneously.
Advantages of multithreading:
Multithreading enables the programmer to write more efficient programs that
take maximum use of CPU by keeping its idle time to a minimum.
Example, if one thread is performing input/output which is a slow process. The
CPU does not have to wait for it to complete. In the meanwhile, it can execute
another thread of the same program. In a single threaded program, the CPU
would have to remain idle till the input/output operation was completed. Thus,
the multi-threaded program improves the overall execution speed.
Multitasking: An ability of the computer to execute more than one program
simultaneously is called Multitasking . There are two types of multitasking.
- Process based
- Thread based
Process based: A process is a program in execution. Thus, process based
multitasking is the feature that allows the computer to run two or more programs
concurrently.
Example: Process based multitasking enables to run the java compiler at the
same time when using the text editor word and excel.
Thread based: In a thread based multitasking environment, the thread is the
smallest unit of dispatchable code. i.e., a single program can perform two or
more tasks simultaneously. For instance, a text editor can format text at the same
time it is printing. These two actions are being performed by two separate
threads.
Java programs that we have seen and discussed so far contain only a single

sequential flow of control. The programs can be called as single threaded
programs . A thread is similar to a program that has a single flow of control.
A unique property of java is, it supports multithreading i.e., java enables us to
use multiple flows of control in developing programs. Each flow of control may
be thought of as a separate tiny program known as thread that runs in parallel to
others. A program that contains a multiple flow of control is known as a
multithreaded program. In the following figure you can figure out that the java
program has four threads, one main and three others. The main thread is the
main module which is designed to create and start the other three threads. Thread
A, B and C.

Once initiated by the main thread, the threads A, B and C run concurrently and
share the resources jointly. Since, threads in java are subprograms of a main
application program and share the same memory space they are known as light
weight thread or processes .
Creating thread: threads are implemented in the form of objects that contain a
method called run() . The run() method has the following syntax:
public void run()
{
Statements for implementing thread
}
The run() method should be invoked by an object of the concerned thread. This
can be achieved by creating the thread and initializing it, with the help of another
thread method “ start() ”. A new thread can be created in two ways.
- By creating a thread class: define a class that extends Thread class and
override its run method.

- By converting a class to a thread: define a class that implements a
runnable interface. The runnable interface has only one method run() that
is to be defined in the method with the code to be executed by the thread.
Example:
class Xyz implements Runnable
{
int i;
public void run()
{
while(true)
{
System.out.println("Hello "+i++);
if(i==5)
break;
}
}
}
public class ThreadTest
{
public static void main(String args[])
{
Xyz r=new Xyz();
Thread t=new Thread(r);
t.start();
}
}
Output:
F:\java programs>javac ThreadTest.java
F:\java programs>java ThreadTest

Hello 0
Hello 1
Hello 2
Hello 3
Hello 4
// Write a program to demonstrate implementation of a thread
class myThread implements Runnable
{
public void run()
{
for(int i=5;i>0;--i)
System.out.println("Child Thread: "+i);
System.out.println("Exiting child thread");
}
}
class Demo
{
public static void main(String args[])
{
myThread r=new myThread();
Thread t=new Thread(r);
t.start();
for(int i=5;i>0;--i)
System.out.println("Main thread: "+i);
System.out.println("Exiting main thread");
}
}
Output:
F:\java programs>javac Demo.java

F:\java programs>java Demo
Child Thread: 5
Child Thread: 4
Main thread: 5
Child Thread: 3
Main thread: 4
Child Thread: 2
Child Thread: 1
Main thread: 3
Main thread: 2
Exiting child thread
Main thread: 1
Exiting main thread
Extending the thread class: A class can be made as a runnable thread by
extending the thread class, this gives access to all the thread methods directly. It
includes the following steps:
- Declare the class as extending the thread class.
- Implement the run() method that is responsible for executing the sequence
of code that the thread will execute.
- Create a thread object and call the start method to initiate the thread
execution.
Declaring the class:
The thread class can be extended as shown below,
class DemoThread extends Thread
{
…
}
DemoThread is a new type of thread.
Implementing the run() method: the run() method has been inherited by the class
DemoThread has to be overridden in order to implement the code to be executed

by the thread.
public void run()
{
// Thread code
}
Starting a new thread: The following statement is used to create and run an
instance of a thread class DemoThread.
DemoThread t=new DemoThread(); // initiates a new object
t.start(); // start() method call
The first statement initializes a new object of class DemoThread. The thread that
will run this object is not yet running. The thread is in a new born state.
The second statement calls the start() method causing the thread to move into the
runnable state. Then the java runtime will schedule the thread to run by invoking
its run method. Now the thread is set to be in the running state. The above two
statements can be replaced by the following statement.
new DemoThread().start();
// Write a program to demonstrate extending thread
class A extends Thread
{
public void run()
{
System.out.println("In Class A");
}
}
class B extends Thread
{
public void run()
{
System.out.println("In Class B");
}

}
class C extends Thread
{
public void run()
{
System.out.println("In Class C");
}
}
class ThreadDemo
{
public static void main(String args[])
{
new A().start();
new B().start();
new C().start();
}
}
Output:
F:\java programs>javac ThreadDemo.java
F:\java programs>java ThreadDemo
In Class A
In Class B
In Class C
Stopping and blocking a thread: a running thread can be stopped by calling
stop() method as shown below,
t.stop();
Here, ‘t’ is an object of the Thread class created and called stop() function using
it.
This statement makes the thread enter into a dead state. However, the thread

enters into dead state automatically when it comes to an end of its method. The
stop() method is used to force the thread to enter a dead state before its natural
death.
By using one of the following three thread methods we can make a thread
temporarily suspended or blocked from entering into the runnable and running
state.
sleep(): blocked for a specified time and the thread will return to the runnable state
when the specified time is elapsed.
suspend(): blocked until further orders and the thread will return to the runnable
state when the resume() method is called.
wait(): blocked until a certain condition occurs and the thread will return to the
runnable state when the notify() method is called.
Life cycle of a thread: At any given time a thread can be in one the following
five states:
- New born state
- Runnable state
- Running state
- Blocked state
- Dead state

New born state: A new thread object created is in a newborn state. This new
born thread is not scheduled for running. At this stage we can schedule it for
running using start() method or kill it using stop() method.
If this thread object is scheduled, then it moves to the runnable state. If we try
any other method at this stage, an exception will be thrown. The following figure
shows the scheduling of a newborn thread.

Runnable state: in this state the thread is ready for execution and waiting for the
availability of the processor. That means the thread has joined the queue and is
waiting for its turn. If all the threads in a queue have equal priority then they are
given a time slice for execution in round robin (first come first serve) fashion.
The thread relinquishes control, joins the queue at the end and again waits for its
turn. If we want to relinquish control to another thread to equal priority before its
turn comes, then this can be done by using the yield() method as shown below.
Running state: In this state the processor has given the time to the thread for its
execution. The thread executes until it relinquishes its control on its own or it is
preempted by a higher priority thread.
A running thread relinquishes its control when,

- It has been suspended using the suspend() method. A suspended method
can be resumed by using the resume() method.
- It has been made to sleep. It is possible to make a thread to sleep for a
certain amount of time using the sleep(time) method. Here, time is in
milliseconds. The thread can be kept out of the queue for a certain period
of time and re enters into the queue once the time specified is elapsed.
- It has been instructed to wait until some event occurs. We can make a
thread to wait using the wait() method.
Blocked state: A thread is prevented from entering into the runnable state and
running state. This happens when the thread is suspended, sleeping or waiting in
order to satisfy certain requirements. A blocked thread is not dead, it is not
runnable. It can be made to run again.

Dead state: The thread enters into dead state when it completes its execution. It
is a natural death of the thread. It is also possible to move a thread to the dead
state by stopping it using stop() method. A thread can also be killed as soon as it
is born or while it is running or when it is in a blocked state.
/* Write a program to demonstrate the various methods used in a life cycle
of a thread. */
class Y extends Thread
{
public void run()
{
for(int j=0;j<5;j++)
{
if(j==4)
stop();
System.out.println("From thread Y: j="+j);
}
System.out.println("Exiting from Y");
}
}
class Z extends Thread
{
public void run()
{
for(int k=0;k<5;k++)
{
if(k==2)
try
{
sleep(2000);
}

catch(Exception e)
{
}
}
System.out.println("Exiting from Z");
}
}
class ThreadMethods
{
public static void main(String args[])
{
Y ty=new Y();
Z tz=new Z();
System.out.println("Start thread Y");
ty.start();
System.out.println("Start thread Z");
tz.start();
}
}
Output:
F:\java programs>javac ThreadMethods.java
F:\java programs>java ThreadMethods
Start thread Y
Start thread Z
From thread Y: j=0
From thread Y: j=1
From thread Y: j=2
From thread Y: j=3

Exiting from Z
Thread exceptions: in the above program we have seen try and catch blocks
while using sleep() method. This block is necessary because the sleep() method
throws an exception, which should be caught. If we fail to catch the exception,
the program will not compile.
Whenever we call a thread method that is likely to throw an exception, we have
to supply an appropriate exception handler to catch it. It may take one of the
following forms:
1. catch(ThreadDeath e)
{
. . . . . . .
. . . . . . // Killed thread
}
2. catch(InterruptedException ie)
{
. . . . . . .
. . . . . . // Cannot handle it in the current state.
}
3. catch(IllegalArgumentException e)
{
. . . . . . .
. . . . . . // Illegal method argument.
}
4. catch(Exception e)
{
. . . . . . .
. . . . . . // Any other
}
Note: in some situations, a running thread may be preempted because of the
presence of a high priority thread.
Thread priority: So far created threads have equal priority and they share the

CPU on First Come First Serve (FCFS) basis. Java permits us to change the
priority of a thread using the method setPriority() as shown below.
t.setPriority(n);
the thread class defines several priority constants:
MIN_PRIORITY=1
NORM_PRIORITY=5
MAX_PRIORITY=10
By assigning a higher priority to a particular thread the programmer can ensure
that it is given quicker attention by the CPU.
getPriority() method: This method allows the program to find out the priority of
the thread which by default is NORM_PRIORITY.
Example, int k=t.getPriority();
// Write a program to demonstrate thread priority
class A extends Thread
{
public void run()
{
System.out.println("In Class A");
for(int i=1;i<=5;i++)
{
System.out.println("From thread A: i="+i);
}
System.out.println("Exit from A");
}
}
class B extends Thread
{
public void run()
{
System.out.println("In Class B");

for(int j=1;j<=5;j++)
{
System.out.println("From thread A: j="+j);
}
System.out.println("Exit from B");
}
}
class C extends Thread
{
public void run()
{
System.out.println("In Class C");
for(int k=1;k<=5;k++)
{
System.out.println("From thread C: k="+k);
}
System.out.println("Exit from C");
}
}
class ThreadPriority
{
public static void main(String args[])
{
A a=new A();
B b=new B();
C c=new C();
c.setPriority(Thread.MAX_PRIORITY);
b.setPriority(a.getPriority()+1);
a.setPriority(Thread.MIN_PRIORITY);

System.out.println("Start thread A");
a.start();
System.out.println("Start thread B");
b.start();
System.out.println("Start thread C");
c.start();
}
}
Output:
F:\java programs>javac ThreadPriority.java
F:\java programs>java ThreadPriority
Start thread A
Start thread B
In Class A
In Class B
Start thread C
In Class C
From thread C: k=1
From thread A: j=1
From thread A: j=2
From thread A: i=1
From thread A: j=3
From thread C: k=2
From thread C: k=3
From thread A: j=4
From thread A: j=5
Exit from B
From thread A: i=2

From thread C: k=4
From thread C: k=5
From thread A: i=3
Exit from C
From thread A: i=4
From thread A: i=5
Exit from A
Synchronization: Till now we have studied that the threads use their own data
and methods. When they try to use data and methods outside, compete for the
same resources and may lead to serious problems. This problem can be solved
using a technique called synchronization. Java provides a keyword called
synchronized to solve this problem.
// Write a program to demonstrate the use of synchronized
class Table
{
synchronized void printTable(int n)
{
for(int i=1;i<=5;i++)
{
System.out.println(n*i);
Try
{
Thread.sleep(400);
}
catch(Exception e)
{
System.out.println(e);
}
}
}

}
class MyThread1 extends Thread
{
Table t;
MyThread1(Table t)
{
this.t=t;
}
public void run()
{
t.printTable(5);
}
}
class MyThread2 extends Thread
{
Table t;
MyThread2(Table t)
{
this.t=t;
}
public void run()
{
t.printTable(100);
}
}
public class TestSynchronization2
{
public static void main(String args[])
{

Table obj = new Table();//only one object
MyThread1 t1=new MyThread1(obj);
MyThread2 t2=new MyThread2(obj);
t1.start();
t2.start();
}
}
Output:
F:\java programs>javac TestSynchronization2.java
F:\java programs>java TestSynchronization2
5
10
15
20
25
100
200
300
400
500
Implementing the Runnable interface: we know that , we can create in two
ways, one is using the extended Thread class and another by implementing the
Runnable interface. We have studied the former one, now let us learn the latter.
Declare the class as implementing the Runnable interface. Implement the run()
method. Create a thread by defining an object that is instantiated from this
runnable class as the target of the thread. Call the thread’s start() method to run
the thread.
// Write a program to demonstrate implementing the runnable interface
public class DemoThread implements Runnable
{

public static void main(String[] args)
{
DemoThread obj = new DemoThread();
Thread t = new Thread(obj);
t.start();
System.out.println("I am outside of the thread");
}
public void run()
{
System.out.println("I am inside of the thread");
}
}
Output:
F:\java programs>javac DemoThread.java
F:\java programs>java DemoThread
I am outside of the thread
I am inside of the thread
EXERCISES
1. What is multithreading and multitasking?
2. How to create threads? Explain with a programming example.
3. Explain extending thread class with an example.
4. Explain the life cycle of a thread with a neat labelled diagram.
5. Write a program to demonstrate thread methods.
6. What are thread exceptions? List the thread exception.
7. Describe thread priority with a programming example.
8. What is synchronization? Explain with a programming example.
9. Explain how to implement the runnable interface.

CHAPTER 11
JAVA EXCEPTIONS
Introduction: it is very common to make mistakes while writing any computer
programs. Such mistakes made by the programmer are called errors. If there are
errors present in the program, the program may produce an unintended result.
Sometimes programs terminate abruptly. It is always true that, whenever we
throw a garbage, we get garbage i.e., garbage in and garbage out (GIGO). If the
input is not proper then the output is also not proper.
There are two types of errors:
- Compile time errors
- Runtime errors
Compile time errors: these are the errors generated due to typing mistakes of
the programmer. If the programmer does not follow the syntax of the
programming language, such errors are generated. When there is an error, the
java compiler does not produce a .class file.
Let us take a look at the program where there is a syntax error
class FirstProgram
{
public static void main(String args[])
{
System.out.println(“Welcome to the world of Java”) // missing semicolon
}
}
The above program produces an error, showing that there is a missing semicolon.
The error is as shown below.
F:\java programs>javac FirstProgram.java
FirstProgram.java:5: error: ';' expected
System.out.println("Welcome to the world of Java") // missing semicolon
^
1 error

Most of the common syntax errors are,
- Missing out semicolon
- Missing out closing braces
- Wrong spelling
- Not following naming conventions
- Missing quotation marks while using strings.
- Using undeclared variables and so on.
Runtime errors: These are the errors that will not be identified during compile
time and the java compiler successfully generated .class file. But the code is still
erroneous and produces an inappropriate unintended output. This type of error
will occur only when the programmer uses wrong logic.
Most of the common runtime errors are,
- Trying to divide the number by zero.
- Accessing elements of an array which are out of bounds.
- Mismatch in typecasting.
- Writing negative size for an array.
- Converting invalid string to a number.
- Attempting to change the state of a thread illegally and so on.
Let us take an example to show the runtime error,
class RunTimeError
{
public static void main(String args[])
{
int a=5,b=0,c;
c=a/b;
System.out.println("The value of c= "+c);
}
}
F:\java programs>javac RunTimeError.java
F:\java programs>java RunTimeError
Exception in thread "main" java.lang.ArithmeticException: / by zero

at RunTimeError.main(RunTimeError.java:6)
Here, you can observe that we have compiled this program successfully and
produced a RunTimeError.class file. In this example we have tried to divide a
number 5 by zero, which cannot be handled by the system produces a runtime
error. This causes the java program to stop executing further statements, if any.
In order to overcome this limitation, we have a technique in java to handle such
situations gracefully using exceptions .
Exceptions: An exception is a condition caused by a runtime error. We can stop
terminating a java program abruptly once it encounters errors and continue
executing other statements, if any, with no disruption. Whenever java finds a
runtime error, it throws an exception object. We have to have a system to catch it
and take appropriate action about it. This process of taking action and showing
appropriate messages, once a runtime error occurs is called exception handling .
Java exceptions perform to find the errors, once the error hits , the java system
throws an exception. There must be a system to catch this exception and handle the
exception properly without terminating the java program abruptly.
Some of the most common exceptions are listed in the following table.
Exception Cause
ArithmeticException Math errors, such as division by
zero.
NumberFormatException Conversion from string type to
numeric type fails.
ArrayStoreException Trying to store the wrong type of
data in an array.
NullPointerException Referring to a null object.
ArrayIndexOutOfBoundsException Subscript out of range for an array.
StringIndexOutOfBoundException Trying to access a non-existent
character position of a string.
IOException General input output failure.
FileNotFoundException Trying to access non-existing files.
StackOverFlowException When there is stack overflow.
OutOfMemoryException Not enough free memory to allocate.
There are two types of exceptions:

- Checked exceptions: exceptions that are handled in the program using try
and catch blocks. These exceptions are extended from java.lang.Exception
class.
- Unchecked exceptions: exceptions that are handled by JVM. These
exceptions are extended from java.leng.RuntimeException class.
Java exception handling is managed by five keywords,
Try , catch , throw , throws and finally .
The program statements that we want to monitor for exceptions are contained
within a try block. If an exception occurs within the try block, it is thrown and
the code can catch this exception. In catch block system generated exceptions
are automatically thrown by java runtime systems to manually throw an
exception we use the keyword throws and exceptions that are thrown out of the
method must be specified as shown by a throws clause and code that absolutely
must be executed before a method returns is put in a finally block.
try and catch blocks: whenever a runtime error occurs an object of a subclass of
the class exception will be created automatically. If this object is not caught the
program stops immediately. In order for the program to continue with execution
of the remaining statements. This exception object has to be caught. The
exception object has to be caught. The exception handling code in java basically
consists of two parts.
- try block: This block contains the group of statements that are being
monitored for possible runtime errors. If the error occurs in the try block
then only it can be caught by a catch block.
- catch block: This is the art of program that is used to catch the exception
object and take some action. It should specify appropriate data types.
Syntax rule: the catch block must immediately follow the try block. For every
try block, there must be at least one catch block following it or a finally block.
This is depicted in the following figure.

Syntax:
try // generates an exception
{
Statements;
}
catch (Exception-type e) // handles an exception
{

Statements;
}
Other executable statements
// Write a program to demonstrate the use of try and catch.
class TryCatch
{
public static void main(String args[])
{
int a=5,b=0,c;
try
{
c=a/b;
}
catch(ArithmeticException e)
{
System.out.println(“Division by zero”);
}
int d=10;
c=a+d;
System.out.println("The value of c= "+c);
}
}
Output:
F:\java programs>javac TryCatch.java
F:\java programs>java TryCatch
Division by zero
The value of c= 15
Note that, even though there is an error in the above program, it did not

terminate abruptly. The catch block handled the exception and allowed the
program to execute the remaining executable statements present after the
exception by showing Division by zero message.
There are basically two possibilities in exception handling, they are,
- There is no runtime error: Now the entire try block is executed
successfully. The catch block will be skipped and execution continues with
the statement following the catch block.
- There is a runtime error within the try block: Now an exception object is
created and remaining statements of the try block are skipped. If the data
type of this exception object matches with the exception type given in the
catch statement. The object will be passed to the parameter ‘e’. the catch
block now gets executed and after that execution continues with the next
part of the program.
Note: In case, the exception object does not match the exception type given in
the catch statement, the program will crash.
Let us take another program to handle exceptions in command line arguments.
class ArgumentException
{
public static void main(String args[])
{
int n,valid=0,invalid=0;
for(int i=0;i<args.length;++i)
{
try
{
n=Integer.parseInt(args[i]);
++valid;
System.out.println("Valid Integer: "+args[i]);
}
catch(NumberFormatException e)
{
++invalid;

System.out.println("Invalid Integer: "+args[i]);
}
}
System.out.println("Total arguments="+(valid+invalid));
System.out.println("Valid Integers="+valid);
System.out.println("Invalid Integers="+invalid);
}
}
Output:
F:\java programs>javac ArgumentException.java
F:\java programs>java ArgumentException 12.2 10 5.5 program 5
Invalid Integer: 12.2
Valid Integer: 10
Invalid Integer: 5.5
Invalid Integer: program
Valid Integer: 5
Total arguments=5
Valid Integers=2
Invalid Integers=3
Nested try block: we can use nested try statements where there is a possibility of
multiple exceptions of different types within a particular block.
Syntax:
try
{
…
…
try
{

…
…
}
catch(Exception-type1 e)
{
…
…
}
catch(Exception-type2 e)
{
…
…
}
}
Let us look at an example on nested try blocks
class NestedTry
{
public static void main(String args[])
{
try
{
int a=args.length;
int b=42/a;
System.out.println("a="+a);
try
{
b=42/(a-1);
int c[]={23,55,78};
c[3]=51;

}
catch(ArrayIndexOutOfBoundsException e)
{
System.out.println("Subscript out of range");
}
System.out.println("After inner try-catch block");
}
catch(ArithmeticException e)
{
System.out.println("Division by zero");
}
}
}
Output:
Run 1:
F:\java programs>javac NestedTry.java
F:\java programs>java NestedTry
Division by zero
Run 2:
F:\java programs>java NestedTry one
a=1
Division by zero
Run 3:
F:\java programs>java NestedTry one two
a=2

Subscript out of range
After inner try-catch block
In case, they don’t match then the catch block of outer try will be inspected for
possible match.
Multiple catch block statements: In some cases more than one exception could
be raised by a single piece of code. To handle this type of situation we can
specify two or more catch clauses. Each catching a different type of exception.
When an exception is thrown each catch statement is inspected in order and the
first one whose type matches that exception is executed. After one catch
statement executes the others are bypassed and execution continues after the try-
catch block.
The general form is,
try
{
Statements; //generates an exception
}
catch(Exception-type1 e)
{
Statements; // process exception type1
}
catch(Exception-type2 e)
{
Statements; // process exception type2
}
…
catch(Exception-type-n e)
{
Statements; // process exception type-n
}
…
Java does not require any processing of the exception; we can simply have a

catch statement with an empty block to avoid program termination.
Example:
catch(Exception e);
the catch statement ends with a semicolon, this statement will catch an exception
and ignore it.
Let us have an example on multiple catch blocks.
class MultipleCatch
{
public static void main(String args[])
{
try
{
int a=args.length;
System.out.println("a:"+a);
int b=42/a;
int c[]={1};
c[42]=99;
}
catch(ArithmeticException e)
{
System.out.println("Division by zero: "+e);
}
catch(ArrayIndexOutOfBoundsException e)
{
System.out.println("Array index out of bounds: "+e);
}
System.out.println("After try-catch block");
}
}

Output:
Run 1:
F:\java programs>javac MultipleCatch.java
F:\java programs>java MultipleCatch
a:0
Division by zero: java.lang.ArithmeticException: / by zero
After try-catch block
Run 2:
F:\java programs>java MultipleCatch test args[]
a:2
Array index out of bounds: java.lang.ArrayIndexOutOfBoundsException: Index
42 out of bounds for length 1
After try-catch block
Finally block: The finally block is optional. It can be placed either immediately
after a try block or if catch blocks are present then the finally block should be
written after the last catch block.
Syntax:
try
{
…
…
}
catch(Exception-type1 e)
{
…
…
}

catch(Exception-type2 e)
{
…
…
}
finally
{
…
…
}
Note: The finally block basically contains a set of instructions that will always
get executed whether an exception is thrown or there is no exception in the try
block. In case, an exception occurs within the try block for which there is no
matching catch handler even in this situation the finally block will get executed
before the program crashes.
Let us have a program that demonstrates the use of finally block.
class FinallyBlock
{
static void funA()
{
try
{
System.out.println("Inside function A");
throw new RuntimeException();
}
finally
{
System.out.println("Inside function A's finally block");
}
}

static void funB()
{
try
{
System.out.println("Inside function B");
return;
}
finally
{
System.out.println("Inside function B's finally block");
}
}
static void funC()
{
try
{
System.out.println("Inside function C");
}
finally
{
System.out.println("Inside function C's finally block");
}
}
public static void main(String args[])
{
try
{
funA();
}

catch(Exception e)
{
System.out.println("Manual runtime exception caught");
}
funB();
funC();
}
}
Output:
F:\java programs>javac FinallyBlock.java
F:\java programs>java FinallyBlock
Inside function A
Inside function A's finally block
Manual runtime exception caught
Inside function B
Inside function B's finally block
Inside function C
Inside function C's finally block
Note: In the above program any time a function is about to go back to its caller
either because of its uncaught exception or due to an explicit return statement.
The finally block will get executed each time before control returns to the caller.
This feature can be used for closing a data file which may have been opened at
the starting of the function with an intention of closing it before return.
Throwing manual exception: Sometimes there may be no run time errors as far
as java is concerned but it may be AWT error as far as our program logic is
concerned.
Example: if we enter negative marks in such a situation the programmer can
manually create an object of a subclass of the class exception and throw it using
the throw statement as shown in the following program.
import java.io.*;

class MyException extends Exception
{
MyException(String msg)
{
super(msg);
}
}
class Test
{
public static void main(String args[])
{
int m;
try
{
DataInputStream in=new DataInputStream(System.in);
System.out.println("Enter the marks");
String s=in.readLine();
m=Integer.parseInt(s);
if(m<0)
{
MyException obj=new MyException("Negative marks");
throw obj;
}
if(m>100)
throw new MyException("Marks above 100");
System.out.println("Valid marks, process it");
}
catch(NumberFormatException e)
{

System.out.println("Not a valid integer");
}
catch(MyException e)
{
System.out.println(e.getMessage());
}
catch(IOException e)
{
System.out.println(e);
}
}
}
Output:
Run 1:
F:\java programs>javac Test.java
F:\java programs>java Test
Enter the marks
abc
Not a valid integer
Run 2:
F:\java programs>java Test
Enter the marks
101
Marks above 100
Throws clause: whenever a function throws an exception of the class
IOException or any of its subclasses, then the programmer has to do one of two
things.

- Place a statement that calls the function inside the try block with the
appropriate catch block as shown.
void fun1()
{
…
…
try
{
…
…
fun2()
…
…
}
catch(IOException e)
{
…
…
}
}
void fun2() throws IOException
{
…
…
}
- Suppose you don’t place the function statement inside the try block then
you must write the throws clause in the header of the calling as shown.
void fun1() throws IOException
{
…
…
fun2()
}
void fun2() throws IOException
{
…
…
}

Note: By using the throw clause in the header of the function 1 we are
informing the java compiler that catching this error becomes the
responsibility of the function that calls function 1.
Hierarchy of exception classes:

EXERCISES
1. What is an exception? Write a program to demonstrate an exception.
2. Explain multiple catch block statements with an example.
3. Describe how to throw manual exceptions? Explain with an example.
4. What is the use of the throws clause? Explain with examples.
5. How to use finally block? Explain with a programming example.
6. Explain nested try and catch blocks with an example.

CHAPTER 12
APPLET PROGRAMMING
Introduction: Java Applets are small java programs that are used in internet
programming that are transported over the internet and executed within an
HTML document. Most of the programming today has become window based.
input output is done through suitable components like labels, textbox, buttons
etc. thus, applet satisfies two requirements.
- Networking: Having a program at one site and running it on another site.
- Windowing: Showing the program's effect in a small rectangular portion
of the screen.
There are two types of applets:
Local applets: An applet developed locally and stored in a local system is known
as a local applet.
Remote applets: An applet developed by someone else and stored on a remote
computer connected to the internet.
Advantages of applet: Applet provides a richer browsing experience in the
following ways.
- Cartoon style animation.
- Realtime games.
- Rich 3D graphics.
- Page music that plays in the background.
- Different sounds that play in real time when the user clicks on different
buttons.
Security restrictions on applets:
Since, applets are downloaded from remote computers some of them may come
from untrusted sources so the following restrictions are imposed on applets.
- Applets cannot open any file on the client computer’s hard disk either for
reading or for writing.
- Applet cannot execute any program from the local hard disk.
- Applet cannot communicate with another server on the network.
Difference between applications and applets:
Applications Applets

Uses main() method to execute Do not use the main() method to
programs. execute programs.
Applications can run independently. Applets cannot run independently.
They run from inside the web page.
Applications can read or write to the Applets cannot read from or write to
files. the files.
Can run any program from a local Applet cannot run any program from
computer. a local computer.
Applications use libraries from other Applets are restricted to use libraries
languages such as C or C++. from other languages.
Executed by java interpreter. Executed by a java enabled browser
or a utility called applet viewer.
User input output is accomplished User IO performs the graphical
using string IO class. interface provided by AWT.
Let’s begin writing a first applet program.
/*<html>
<applet code="FirstApplet.class" width=200 height=200>
</applet>
</html>*/
import java.awt.*;
import java.applet.*;
public class FirstApplet extends Applet
{
public void paint(Graphics g)
{
g.drawString("Welcome to the world of java applets",10,100);
}
}
Note: paint() method is called every time the applet has to re-display its output,
the paint() method gets invoked in the following situations.
- When the applet begins the application.
- The applet window is maximized and then restored.

- The applet window is resized.
- The applet window is covered by another window and then uncovered.
- When the window if referred.
drawString() method receives a single parameter. An object of the class graphics.
This object is used to invoke the method drawString() which is defined in the
class Graphics.
void drawString(String message, int x, int y);
it will display the given message at the specified xy location.
How to execute a java applet?
- Type the source code using edit and save it in a file with the same name as
the public class with extension .java .
i.e., C:\Program Files\Java\jdk\bin> edit FirstApplet.java
- Compile the program as usual using the java compiler to produce the .class
file containing the byte code.
C:\Program Files\Java\jdk\bin>javac FirstApplet.java
- Create an HTML file using edit containing appropriate applet tag.
<html>
<applet code="FirstApplet.class" width=200 height=200>
</applet>
</html>
This file should be stored with extension .html .
FirstApplet.html
- Execute the program either using applet viewer or through a browser
software.
C:\Program Files\Java\jdk\bin>appletviewer FirstApplet.html
Executing java applet using browser:
- Start internet explorer and in the address bar type the full path and name
of the html file.
More convenient method for testing applets: when executing an applet program
using the applet viewer, it is not compulsory to create a separate HTML file each
time. Instead you can include a comment containing the applet tag in the source
program itself as shown.
/*<html>

<applet code="FirstApplet.class" width=200 height=200>
</applet>
</html>*/
Now execute it, type the following command.
C:\Program Files\Java\jdk\bin>appletviewer FirstApplet.java
The appletviewer software now searches for the comment in the source file.
Inside the comment, it encounters the applet tag and within it, it finds the name
of the .class file which is to be executed.
Note: To run applets we have to install JDK 8 because the latest java version
doesn’t support appletviewer. I am storing all java source files in F: drive of my
PC under the folder called java programs. Hence, the path F:\java programs> is
used.
Output:
F:\java programs>javac FirstApplet.java
F:\java programs>appletviewer FirstApplet.java
Applet life cycle (Applets state transition diagram): The applet class contains
all the necessary methods for starting, executing and stopping the applet
program.

- init(): This is the first method to be called as soon as the applet program is
loaded into the memory. This method gets executed into the memory. This
method gets executed only once during the lifetime of an applet. So any
setting which is required to be done only once should be done inside this
method. Such as, setting the background, foreground colors of the applet.
- start(): This method will be called immediately after init() method. But
whereas, init() method is called only once, the start() method will be called
each time. The web page containing the applet is displayed on the screen.
Thus, if the user minimizes the window, restores it or if the user leaves the
webpage by clicking on a hyperlink and comes back to it later. The applet
program resumes execution from the start() method.
- stop(): This method will be called automatically each time the user
minimizes the window or leaves the web page containing the applet. The
stop() method can also be called explicitly by the programmer.
- destroy(): This method is called automatically just before the applet is

completely removed from the memory any resources which the applet may
have been using should be freed in this method.
Note: the stop() method will also be called before destroy() method.
// Write a program to illustrate the applet methods.
/*<html>
<applet code="AppletFunctions.class" width=300 height=200>
</applet>
</html>*/
import java.awt.*;
import java.applet.*;
public class AppletFunctions extends Applet
{
String msg;
public void init()
{
setBackground(Color.cyan);
setForeground(Color.red);
msg="init()";
}
public void start()
{
msg+="start()";
}
public void paint(Graphics g)
{
msg+="paint()";
g.drawString(msg,10,30);
showStatus("Applet is running");
}

public void stop()
{
msg="stop()";
System.out.println(msg);
}
public void destroy()
{
msg+="destroy()";
System.out.println(msg);
}
}
The method setBackground() and setForeground() are defined in the class
component which is the superclass of applet. The default background color is
light grey and the default foreground color is black. The foreground color
determines the color of the text and the graphics that will be drawn in the applet.
showStatus() method is used to display information in the status bar to give the
user some feedback about what is happening in the program or to report any
error.
Output:
F:\java programs>javac AppletFunctions.java
F:\java programs>appletviewer AppletFunctions.java
stop()
stop()destroy()

Passing parameters to applets: we can pass user defined parameters to an
applet using <PARAM> tag. It has the property called name such as color and a
VALUE attribute. The applet can refer to that parameter by name to find its
value. Let us look at an example.
import java.awt.*;
import java.applet.*;
public class ParamDemo extends Applet
{
String msg;
public void init()
{
msg=getParameter("sting"); // parameter value to be received
if(msg==null)
msg="there!";
msg="Hello "+msg;
}
public void paint(Graphics g)
{
g.drawString(msg,10,100);
}
}
Now create an HTML file as shown below and save it as ParamDemo.html
<html>
<applet code="ParamDemo.class" width=300 height=200>
<PARAM NAME="string" VALUE="Applet!">
</applet>
</html>
Run this program by using applet viewer as below,
F:\java programs >appletviewer ParamDemo.html
Output:

F:\java programs>javac ParamDemo.java
F:\java programs>appletviewer ParamDemo.html
// Write a program to display numerical value.
import java.awt.*;
import java.applet.*;
public class numbers extends Applet
{
public void paint(Graphics g)
{
int a=10;
int b=20;
int c=a+b;
String s="Sum="+String.valueOf(c);
g.drawString(s,100,100);
}
}
Save this file as numbers.java and now create another file called numbers.html
and write the following code.
<html>
<body>
<applet code="numbers.class" width=300 height=200>
</applet>

</body>
</html>
Output:
F:\java programs>javac numbers.java
F:\java programs>appletviewer numbers.html
We can also write a program to have interaction of the user as follows.
import java.awt.*;
import java.applet.*;
public class Interaction extends Applet
{
TextField text1,text2;
public void init()
{
text1=new TextField(10);
text2=new TextField(10);
add(text1);
add(text2);
text1.setText("0");
text2.setText("0");
}
public void paint(Graphics g)
{

int x=0,y=0,z=0;
String s1,s2,s;
g.drawString("Input numbers:",10,50);
try
{
s1=text1.getText();
x=Integer.parseInt(s1);
s2=text2.getText();
y=Integer.parseInt(s2);
}
catch(Exception e)
{
}
z=x+y;
s=String.valueOf(z);
g.drawString("The sum is: ",10,70);
g.drawString(s,100,70);
}
public Boolean action(Event event, Object object)
{
repaint();
return true;
}
}
Write an HTML code as shown below.
<html>
<applet code=Interaction.class width=300 height=200>
</applet>
</html>

Output:
F:\java programs>javac Interaction.java
F:\java programs>appletviewer Interaction.html
EXERCISES
1. What are applets? Explain the types of applets.
2. What are the advantages of an applet?
3. Write the security restrictions on the applet.
4. Differentiate between application programs and applet programs.
5. Write the steps to run java applet.
6. Explain the life cycle of an applet with a programming example.
7. Write a program to demonstrate passing parameters to applets.
8. Write a program to display numerical values.

CHAPTER 13
GRAPHICS PROGRAMMING
Introduction: We can write java applets to draw lines, different geometrical
shapes, images and text of various font styles. We can also include colors in the
display.
Graphics class: The graphics class contains several methods for drawing
different geometrical shapes such as, line, rectangle, circle etc. The geometrical
figures can be drawn using the coordinate system of mathematics.
- drawLine(): This method is used to draw a line that takes four coordinates
as shown below.
g.drawLine(x1,y1,x2,y2);
It draws the straight line segment from starting point (x1,y1) to the ending
point (x2,y2).
- drawRect(): This method draws a rectangle with (xt,yt) as the coordinates
of the top left corner points with w pixels and height h pixels.
g.drawRect(xt,yt,w,h);

- fillRect(): This method draws a shaded rectangle, filled with the color.
- drawRoundRect(): This method draws a rectangle with rounded corners
where wc and hc are the width and height of the corners respectively.
g.drawRoundRect(xt,yt,w,h,wc,hc);
- fillRoundRect(): This method draws a rounded rectangle filled with the
current color.
g.fillRoundRect(xt,yt,w,h,wc,hc);
- drawOval(): This method draws an ellipse or a circle with (xt,yt) as the
top left corner point of an imaginary bounding rectangle lx and ly length
along x and y axes respectively.

- fillOval(): This method draws and fill ellipse of the circle.
g.fillOval(xt,yt,lx,ly);
- clearRect(): This method clears the rectangular area of the applet by
filling it with the background color.
Ex: g.clearRect(100,75,100,75);
- drawArc(): This method will draw an arc bounded by the imaginary
rectangle with starting angle of s degrees and angle of a degrees.
Example: The positive x axis is taken as 0 0 anticlockwise direction is
considered as positive and clockwise direction is considered as negative.
g.drawArc(0,0,80,80,90,90);

- fillArc(): This method will draw a shaded arc like the slice of the pie
chart.
g.fillArc(xt,yt,w,h,s,a);
g.fillArc(50,20,80,60,90,-45);
- drawPolygon(): It will draw a set of n-1 connected lines segments joining
the point whose x-coordinate values are stored in the array x and y-
coordinate values in the array y.
g.drawPolygon(x,y,n);
- fillpolygon(): This method is used to draw the polygon filled with the
current color.
/* Write a program to draw the following figure in an applet having width
of 300 and height of 250 pixels. */

import java.awt.Graphics;
import java.applet.Applet;
/*<applet code=Poly.class width=300 height=250>
</applet>*/
public class Poly extends Applet
{
public void paint(Graphics g)
{
int x1[]={20,120,220,20};
int y1[]={20,120,20,20};
int x2[]={120,220,220,120};
int y2[]={120,220,20,120};
g.drawPolygon(x1,y1,4);
g.fillPolygon(x2,y2,4);
}
}
Output:
F:\java programs>javac Poly.java
F:\java programs>appletviewer Poly.java

Setting colors: The class color defines the following constants or different colors
as shown below.
Color.black
Color.blue
Color.cyan
Color.darkgray
Color.gray
Color.green
Color.lightgreen
Color.magenta
Color.orange
Color.pink
Color.red
Color.white
Color.yellow
// Write a program to draw various geometrical figures with colors.
import java.applet.*;
import java.awt.*;
/*<applet code=Pattern.class width=300 height=250>
</applet>*/
public class Pattern extends Applet

{
public void paint(Graphics g)
{
g.setColor(Color.blue);
g.drawLine(10,10,50,50);
g.drawLine(10,50,50,10);
g.setColor(Color.green);
g.drawRect(10,60,40,30);
g.setColor(Color.cyan);
g.fillRect(60,10,30,80);
g.setColor(Color.magenta);
g.drawRoundRect(10,100,80,50,20,20);
g.fillRoundRect(20,110,60,30,5,5);
g.setColor(Color.yellow);
g.drawOval(120,30,40,50);
g.setColor(Color.red);
g.drawOval(180,30,50,50);
g.fillOval(180,90,50,50);
}
}
Output:

Sizing the graphics:
- getSize(): This function returns an object class dimension containing the
width and height of the applets window by finding out the dimensions of
the applet the graphical elements can be drawn so as to fit within this
space.
// Write a program to demonstrate the use of sizing graphics.
import java.applet.*;
import java.awt.*;
/*<applet code=SizeMe.class width=300 height=200>
</applet>*/
public class SizeMe extends Applet
{
Dimension d;
public void paint(Graphics g)
{
d=getSize();
g.drawRect(1,1,d.width-2,d.height-2);
g.drawLine(1,1,d.width-1,d.height-1);
g.drawLine(1,d.height-1,d.width-1,1);
}
}
Output:
F:\java programs>javac SizeMe.java
F:\java programs>appletviewer SizeMe.java

Each time the size of the applet window is changed by the user, the point method
will be called automatically from inside the paint method, the getSize() method
is called which will return the new size of the window. The rectangle and
diagonals will be now redrawn using the new dimension.
- setSize(): This function can be used to modify the size of the applet
window (width, height) through the program.
Example: Dimension d=new Dimension(w,h);
setSize(d);
or
setSize(new Dimension(w,h));
Fonts: In order to set the font style and size we have to call the method setFont()
which is defined in the class graphics.
Example: g.setFont(f);
The object f should be created as shown below,
Font f=new Font(s,m,n);
Here, s is string, m is font style, n is font size.
Example: Font f=new Font(“Times New Roman”,Font.bold,24);
Subsequent drawString() method will use this particular font string.
Fontname=”Times New Roman”, “Helretica”, “Courier” Fontstyle: PLAIN,
BOLD, ITALIC.
These are defined in the class Font as shown below,
public static final PLAIN=0;
public static final BOLD=1;
public static final ITALIC=2;

public static final BOLD+ITALIC=3;
// Write a program to demonstrate font styles.
import java.awt.*;
import java.applet.*;
/*<applet code=TextFont width=500 height=300>
</applet>*/
public class TextFont extends Applet
{
public void paint(Graphics g)
{
Font f1=new Font("Helretica",Font.PLAIN,14);
g.setFont(f1);
g.drawString("Java for you",5,35);
Font f2=new Font("Times New Roman", Font.BOLD,24);
g.setFont(f2);
g.drawString("Java for you",5,75);
Font f3=new Font("courier",Font.ITALIC,36);
g.setFont(f3);
g.drawString("Java for you",5,155);
Font f4=new Font("Arial",3,72);
g.setColor(Color.orange);
g.setFont(f4);
g.drawString("Java for you",5,225);
}
}
Output:
F:\java programs>javac TextFont.java
F:\java programs>appletviewer TextFont.java

// Write a program to draw a pie chart using arc
import java.awt.*;
import java.applet.*;
/*<applet code=Pie.class width=500 height=300>
</applet>*/
public class Pie extends Applet
{
public void paint(Graphics g)
{
int a=10,b=6,c=3,d=3,e=2;
int f=360/24;
int a1=f*a;
int a2=f*b;
int a3=f*c;
int a4=f*d;
int a5=f*e;
int s=0;
g.fillArc(50,40,200,200,s,a1);
s+=a1;
g.setColor(Color.yellow);

g.fillArc(50,40,200,200,s,a2);
s+=a2;
g.setColor(Color.green);
g.fillArc(50,40,200,200,s,a3);
s+=a3;
g.setColor(Color.blue);
g.fillArc(50,40,200,200,s,a4);
s+=a4;
g.setColor(Color.red);
g.fillArc(50,40,200,200,s,a5);
}
}
Output:
F:\java programs>javac Pie.java
F:\java programs>appletviewer Pie.java

EXERCISES
1. Explain various geometrical figures with their methods.
2. Write a program to demonstrate polygon.
3. Write a program to illustrate different geometrical figures.
4. Write a program to demonstrate sizing graphics.
5. Write a program to illustrate various font styles.
6. Write a program to demonstrate how to draw a pie chart.

CHAPTER 14
EVENT HANDLING
Introduction: Applets are events driven programs most events are generated by
the user such as, due to a key press or a mouse click. The modern approach in
java for handling events is based on the “Delegation Event Model” (DEM)
whose concept is as follows.
A source generates an event and sends it to one or more listeners
A listener has to register with the source in order to receive an event. Since, the
event will be sent only to those listeners who want to receive it. The listener
waits until it receives it. The listener waits until it receives an event when it is
received it will take the appropriate action.
What is the source?
A source is an object that generates an event; it could be a user interface
component such as a button, scroll bar etc. The source could also be the applet
itself for keyboard and mouse events.
What is meant by event?

An	event	is	basically	a	change	of	state	in	a	source.	In	java	whenever	there	is
such	a	change	of	state,	an	object	of	a	particular	event	class	is	automatically
created	depending	upon	the	type	of	event.
What	is	a	listener?
A	listener	is	an	object	that	gets	notified	when	an	event	occurs;	the	listener	has	to
do	two	things.
-	 	Register	the	source	in	order	to	receive	the	event.

-	 	Implement	the	necessary	method	or	methods	to	process	the	events.

Event	classes:	All	the	event	classes	are	defined	in	API	sub-package	java.awt
event.	Each	different	type	of	event	creates	an	object	of	a	different	event	class	as
shown	in	the	following	table.
Class	name Object	of	this	class	is	created
when…
| ActionEvent | i)		Button	is	clicked. |     |
| ----------- | ---------------------- | --- |

|     | ii)	 | 	Menu	item	is	clicked. |
| --- | ---- | ---------------------- |

iii)	List	item	is	double	clicked.
iv)		Enter	key	is	pressed	in	a	textfield.

| AdjustmentEvent | A	scroll	bar	is	manipulated. |                       |
| --------------- | ---------------------------- | --------------------- |
| ItemEvent       | i)	                          | 	Checkbox	is	clicked. |

|     | ii)	 | 	List	item	is	clicked. |
| --- | ---- | ---------------------- |

|     | iii)	 | 	A	choice	selection	is	made. |
| --- | ----- | ---------------------------- |

|     | iv)	 | 	Checkable	menu	items	are |
| --- | ---- | ------------------------- |

clicked.
| KeyEvent   | A	key	is	pressed	or	relaxed. |                            |
| ---------- | ---------------------------- | -------------------------- |
| MouseEvent | i)	                          | 	Mouse	enters	a	component. |

|     | ii)	 | 	Mouse	leaves	a	component. |
| --- | ---- | -------------------------- |

|     | iii)	 													 | 	Mouse	is	moved.   |
| --- | ------------------- | ------------------ |
|     | iv)	                | 	Mouse	is	dragged. |

|     | v)	 | 	Mouse	is	pressed. |
| --- | --- | ------------------ |

|     | vi)	 | 	Mouse	is	clicked. |
| --- | ---- | ------------------ |

|     | vii)	 | 	Mouse	is	released. |
| --- | ----- | ------------------- |

| TextEvent | Contents	of	the	text	field	are |     |
| --------- | ------------------------------ | --- |
changed.
| WindowEvent | i)	 | 	Windows	is	open. |
| ----------- | --- | ----------------- |

|     |     | ii)	 | 	Windows	is	closing. |     |     |
| --- | --- | ---- | -------------------- | --- | --- |

|     |     | iii)	 | 	Window	is	closed. |     |     |
| --- | --- | ----- | ------------------ | --- | --- |

|     |     | iv)	 | 	Window	is	activated. |     |     |
| --- | --- | ---- | --------------------- | --- | --- |

|     |     | v)	 | 	Window	is	deactivated. |     |     |
| --- | --- | --- | ----------------------- | --- | --- |

|     |     | vi)	 													 | 	Window	is	iconified.    |     |     |
| --- | --- | ------------------ | ------------------------ | --- | --- |
|     |     | vii)	              | 	Window	is	de-iconified. |     |     |

| FocusEvent |     | i)	 | 	Components	get	the	focus. |     |     |
| ---------- | --- | --- | -------------------------- | --- | --- |

|     |     | ii)	 | 	Components	leave	the	focus. |     |     |
| --- | --- | ---- | ---------------------------- | --- | --- |

Event	listener	interface:	The	methods	that	receive	and	process	the	events	are
defined	in	the	set	of	interfaces	which	are	also	stored	in	the	sub-package.
java.awt.event
so,	to	override	these	methods	the	listener	class	must	implement	one	or	more	of
these	interfaces	whenever	an	event	occurs.	The	appropriate	method	gets
automatically	invoked	and	the	event	object	is	passed	to	it	as	a	parameter.
| Interface | Defines	the	methods |     |     | Invoked |     |
| --------- | ------------------- | --- | --- | ------- | --- |
when
ActionListener actionPerformed() Action public	void	actionPerformed(ActionEvent
|     |     |     |     | event | ae) |
| --- | --- | --- | --- | ----- | --- |
occurs
AdjustmentListener adjustmentValueChange() Adjustment public	void
|     |     |     |     | event  | adjustmentValueChange(AdjustmentEvent |
| --- | --- | --- | --- | ------ | ------------------------------------- |
|     |     |     |     | occurs | ae)                                   |
ItemListener itemStateChanged Items	event public	void	itemStateChanged(ItemEvent
|     |     |     |     | occurs | ie) |
| --- | --- | --- | --- | ------ | --- |
KeyListener keyPressed() Key	event public	void	keyPressed(KeyEvent	ke)
|     | keyTyped() |     |     | occurs | public	void	keyTyped(KeyEvent	ke) |
| --- | ---------- | --- | --- | ------ | --------------------------------- |
keyRelaxed()
| MouseListener | mouseEntered() |     |     | Mouse | 	   |
| ------------- | -------------- | --- | --- | ----- | --- |
events
mouseExited()
|     | mousePressed() |     |     | occurs |     |
| --- | -------------- | --- | --- | ------ | --- |
mouseClicked()
mouseReleased()
mouseMoved()
mouseDragged()

MouseMotionListener mouseMoved() As	the public	void	mouseMoved(MouseEvent
|     | 	   | mouse	is | me) |
| --- | --- | -------- | --- |
moved.
|     | 	   |     | 	   |
| --- | --- | --- | --- |

|     | 	   |     | 	   |
| --- | --- | --- | --- |
As	the
mouseDragged()
public	void	mouseDragged(MouseEvent
mouse	is
me)
dragged.

TextListener textChanged() Text	events public	void	textChanged(TextEvent	te)
occur.
| WindowListener | windowOpened()  | Window | public	void                  |
| -------------- | --------------- | ------ | ---------------------------- |
|                | windowClosing() | event  | windowOpened(WindowEvent	we) |
occurs.
|     | windowClosed() |     | public	void |
| --- | -------------- | --- | ----------- |
windowClosing(WindowEvent	we)
windowActivated()
|     | windowDeactivated() |     | public	void	windowClosed(WindowEvent |
| --- | ------------------- | --- | ------------------------------------ |
we)
windowIconified()
|     | windowDeiconified() |     | public	void |
| --- | ------------------- | --- | ----------- |
windowActivated(WindowEvent	we)
public	void
windowDeactivated(WindowEvent	we)
public	void
windowIconified(WindowEvent	we)
public	void
windowDeiconified(WindowEvent	we)
FocusListener focusGained() Focus public	void	focusGained(FocusEvent	fe)
|     | focusLost() | event | public	void	focusLost(FocusEvent	fe) |
| --- | ----------- | ----- | ------------------------------------ |
occurs.
javap	java.awt.event.ActionEvent.ActionListener
//	Write	a	program	to	demonstrate	mouse	event	handlers
import	java.awt.*;
import	java.awt.event.*;
import	java.applet.*;
/*<applet	code=MouseDemo.class	width=500	height=200>
</applet>*/
public	class	MouseDemo	extends	Applet	implements	MouseListener,

MouseMotionListener
{
String msg;
int mx,my;
public void init()
{
msg="init";
this.addMouseListener(this); // this is the general form of the statement for
//registration of listener with source
this.addMouseMotionListener(this);
}
public void mouseClicked(MouseEvent me)
{
int mx=0,my=10;
msg="Mouse Clicked";
repaint();
}
public void mouseEntered(MouseEvent me)
{
int mx=0,my=10;
msg="Mouse Entered";
repaint();
}
public void mouseExited(MouseEvent me)
{
int mx=0,my=10;
msg="Mouse Exited";
repaint();
}

public void mousePressed(MouseEvent me)
{
mx=me.getX();
my=me.getY();
msg="Down";
repaint();
}
public void mouseReleased(MouseEvent me)
{
mx=me.getX();
my=me.getY();
msg="Up";
repaint();
}
public void mouseDragged(MouseEvent me)
{
mx=me.getX();
my=me.getY();
msg="*";
showStatus("Dragging mouse at "+mx+","+msg);
repaint();
}
public void mouseMoved(MouseEvent me)
{
showStatus("Moving mouse at "+me.getX()+","+me.getY());
repaint();
}
public void paint(Graphics g)
{

g.drawString(msg,mx,my);
}
}
Output:
F:\java programs>javac MouseDemo.java
F:\java programs>appletviewer MouseDemo.java
User interface components: The awt package has various classes such as,
- Label
- Textfield
- Button
- Scrollbar
- Checkbox etc.
To include any of these components in the applet we have to first create an
object of the relevant class and then add this object to the applet by calling the
add() method.
Label: A label is meant for displaying messages to the user. It is a passive control
which the user cannot interact with. It cannot generate any events.
Constructors:
Label lb1=new Label(); // creates blank label
Label lb2=new Label(str) // creates a label with the given caption
Label lb3=new Label(str,alignment) creates the label with the given caption.
Alignment specifies the position of the caption inside the label following are the
options,

LABEL.LEFT
LABEL.RIGHT
LABEL.CENTER
Methods:
setText(): This method can be called to change the caption on the label.
lbl.setText(str);
getText(): It returns the caption of the label.
String s=lbl.getText();
// Write a program to demonstrate the use of label
import java.awt.*;
import java.applet.*;
/*<applet code="LabelDemo.class" width=300 height=200>
</applet>*/
public class LabelDemo extends Applet
{
String s;
public void init()
{
Label l1=new Label();
Label l2=new Label("for",Label.CENTER);
Label l3=new Label("YOU");
l1.setBackground(Color.blue);
l2.setBackground(Color.red);
l3.setBackground(Color.yellow);
l1.setText("Java");
l1.setAlignment(Label.RIGHT);
add(l1);
add(l2);
add(l3);

s=l1.getText()+l2.getText()+l3.getText();
}
public void paint(Graphics g)
{
g.drawString(s,10,200);
}
}
Output:
F:\java programs>javac LabelDemo.java
F:\java programs>appletviewer LabelDemo.java
Text field: Text field allows the user to enter the text data and also to edit it.
Constructors:
1. TextField t1=new TextField();
TextField t1=new TextField(n);
TextField t1=new TextField(s,n);
2. Creates text field n-characters wide.
3. Creates a text field with default text ‘s’ and with ‘n’
Methods:
1. getText(): Returns the current text of the text field.
String txt=t1.getText();
2. setText(): Assigns the specified string to the text field.
t1.setText(String);

3. getSelectedText(): Returns the part of the text which is highlighted by the
user.
String txt=t1.getSelectedText();
4. setEchoChar(): For entering password that should not be displayed on the
screen. This method can be used to specify some other character which
should be displayed during data entry.
setEchoChar(‘*’);
// Write a program to demonstrate the use of a text field.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=TextDemo width=500 height=300>
</applet>*/
public class TextDemo extends Applet implements ActionListener
{
TextField txtname, txtpwd;
public void init()
{
Label lblname=new Label("Enter Userid",Label.RIGHT);
Label lblpwd=new Label("Enter password",Label.RIGHT);
txtname=new TextField(12);
txtpwd=new TextField(8);
txtpwd.setEchoChar('*');
add(lblname);
add(txtname);
add(lblpwd);
add(txtpwd);
txtname.addActionListener(this);
txtpwd.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)

{
repaint();
}
public void paint(Graphics g)
{
g.drawString("Userid :"+txtname.getText(),6,80);
g.drawString("Selected Text :"+txtname.getText(),6,100);
g.drawString("Password is :"+txtpwd.getText(),6,120);
}
}
Output:
F:\java programs>javac TextDemo.java
F:\java programs>appletviewer TextDemo.java
// Write a program to illustrate button event handlers.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=ButtonDemo width=500 height=300>
</applet>*/

public class ButtonDemo extends Applet implements ActionListener
{
TextField t1,t2;
Button b1,b2;
String msg;
public void init()
{
Label l1=new Label("Enter the number: ");
t1=new TextField(10);
t2=new TextField(10);
b1=new Button("OK");
b2=new Button("Cancel");
add(t1);
add(t2);
add(l1);
add(b1);
add(b2);
t1.setText("0");
t2.setText("0");
b1.addActionListener(this);
b2.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
Button b=(Button)ae.getSource();
if(b==b1)
{
int x=0,y=0,z;
String s1=" ";

String s2=" ";
try
{
s1=t1.getText();
x=Integer.parseInt(s1);
s2=t2.getText();
y=Integer.parseInt(s2);
z=x+y;
msg=("Sum is: "+z);
}
catch(Exception e)
{
msg="Invalid Integer";
}
}
else
msg="Operation cancelled";
repaint();
}
public void paint(Graphics g)
{
g.drawString(msg,10,110);
}
}
Output:
F:\java programs>javac ButtonDemo.java
F:\java programs>appletviewer ButtonDemo.java

Scroll bars: Scroll bars are used to select some value between a specified
minimum and maximum they may be oriented either horizontal or vertical.
Creating a scroll bar:
Scrollbar s1=new Scrollbar();
By default is creates vertical scroll bar
Scrollbar s2=new Scrollbar(style); // style is of int type
Scrollbar VERTICAL or 1
Scrollbar HORIZONTAL or 0
Scrollbar s3=new Scrollbar(style, inti value, thumbsize, min,max);
init value represents the initial value of the scrollbar. Thumb size is the number
of pixels representing the thickness of the scrollbar min and max are the
minimum and maximum settings of the scroll bar.
Methods:
setValues(): If the scrollbar has been created using either of the first two
constructors, it needs to be set by calling this method.
s1.setValues(init value, thumbsize, min,max);
getValues(): Returns the present value of the scroll bar.
int n=s1.getValues();
setValue(): Sets the value of the scrollbar through the program.
s1.setValue(k); // k is of int type
or

s1.setValue(s1.getValue()+5);
getMinimum(): Getting minimum settings of the scrollbar
ex: int m1=s1.getMinimum();
getMaximum(): Returns the maximum setting of the scroll bar
int m2=s1.getMaximum();
setUnitIncrement(): It is used to specify the amount by which the value of
scrollbar should increase or decrease when either side arrows are clicked. The
default is one.
Ex: s1.setUnitIncrement(5);
setBlockIncrement(): This method is used to specify the amount by which the
value of the scroll bar should increase or decrease whenever the user clicks on
either side of the thumb. Default is 10.
Ex: s1.setBlockIncrement(25);
Handling scrollbars methods: To process the scroll bar events we should
implement the adjustment listener interface which contains the single method.
adjustmentValueChanged(AdjustmentEvent ae)
Every time the user interacts with the scrollbar an adjustment event object is
created and passed to this method as an argument.
Laying the components manually: All the components so far have been
positioned by the default layout manager which automatically places the
components from left to right and top to bottom inside the applet. In order to lay
the controls manually, the layout manager must be first disabled. This is done by
calling the method setLayout() using a NULL object.
Ex: setLayout(NULL); // disables the layout manager
setBounds(): This method is called to specify the size as well as the position of
each of the components manually.
obj.setBounds(xt,yt,w,h);
where, xt – left
yt – top
w – width
h – height
s1.setBounds(120,100,50,10);

// Write a program to display a vertical and horizontal scrollbar.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="ScrollDemo.class" width=500 height=300>
</applet>*/
public class ScrollDemo extends Applet implements AdjustmentListener,
MouseMotionListener
{
String msg;
Scrollbar vs,hs;
public void init()
{
msg=" ";
setLayout(null);
vs=new Scrollbar(1,0,1,0,150);
hs=new Scrollbar(0,0,1,0,200);
vs.setBounds(1,1,20,275);
hs.setBounds(25,1,350,20);
add(vs);
add(hs);
vs.addAdjustmentListener(this);
hs.addAdjustmentListener(this);
this.addMouseMotionListener(this);
}
public void adjustmentValueChanged(AdjustmentEvent ae)
{
repaint();
}

public void mouseDragged(MouseEvent me)
{
int x=me.getX();
int y=me.getY();
vs.setValue(y);
hs.setValue(x);
repaint();
}
public void paint(Graphics g)
{
msg="Vertical: "+vs.getValue();
msg+="Horizontal: "+hs.getValue();
g.drawString(msg,30,90);
g.drawString("*",hs.getValue(),vs.getValue());
}
public void mouseMoved(MouseEvent me)
{
}
}
Output:
F:\java programs>javac ScrollDemo.java
F:\java programs>appletviewer ScrollDemo.java

Checkboxes: A checkbox is a control that can be used to turn an option on or off.
Constructors:
Checkbox cb1=new Checkbox(); // creates a blank checkbox
Checkbox cb2=new Checkbox(str); // creates a checkbox with given caption
Checkbox cb3=new Checkbox(str,flag); // if flag is true then the checkbox
// will come initially selected.
getState(): Returns the present state of the check box.
Ex: boolean b=cb1.getState();
‘b’ will be assigned the value true if ‘cb1’ is selected otherwise, false.
setState(): This method can be called to set the start of the checkbox through
the program.
cb1.setState(b);
getLabel(): Returns the caption of the checkbox.
Ex: String s=cb1.getLabel();
setLabel(): Sets the caption of the checkbox.
cb1.setLabel(str);
Handling checkboxes: every time a checkbox is selected or deselected by
clicking on it. An object of the class item event is created. Whenever listener wants
to receive this event one must implement the item listener interface which

defines the method itemStateChanged() method which gets called automatically
whenever an item event occurs.
// Write a program to demonstrate a checkbox.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=CheckboxDemo width=300 height=200>
</applet>*/
public class CheckboxDemo extends Applet implements ItemListener
String msg;
Checkbox cb1,cb2,cb3;
public void init()
cb1=new Checkbox("Car",true);
cb2=new Checkbox("Bicycle");
cb3=new Checkbox("Scooter");
cb1.addItemListener(this);
cb2.addItemListener(this);
cb3.addItemListener(this);
public void itemStateChanged(ItemEvent ie)
public void paint(Graphics g)

msg="Current State:";
g.drawString(msg,5,50);
msg=cb1.getLabel()+":"+cb1.getState();
g.drawString(msg,5,70);
msg=cb2.getLabel()+":"+cb2.getState();
g.drawString(msg,5,90);
msg=cb3.getLabel()+":"+cb3.getState();
g.drawString(msg,5,110);
F:\java programs>javac CheckboxDemo.java
F:\java programs>appletviewer CheckboxDemo.java
Array of checkbox:
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=CheckboxArray.class width=300 height=200>
</applet>*/
public class CheckboxArray extends Applet implements ItemListener

{
String msg;
Checkbox cb[];
public void init()
{
cb=new Checkbox[3];
cb[0]=new Checkbox("Car",true);
cb[1]=new Checkbox("Bicycle");
cb[2]=new Checkbox("Scooter");
for(int i=0;i<3;i++)
{
add(cb[i]);
cb[i].addItemListener(this);
}
}
public void itemStateChanged(ItemEvent ie)
{
repaint();
}
public void paint(Graphics g)
{
msg="Current state:";
g.drawString(msg,6,80);
for(int i=0;i<3;i++)
{
msg=cb[i].getLabel()+":"+cb[i].getState();
g.drawString(msg,6,100+20*i);
}
}

}
Output:
F:\java programs>javac CheckboxArray.java
F:\java programs>appletviewer CheckboxArray.java
Identifying which checkbox was clicked:
- getItemSelectable(): This method is defined in the ItemEvent class, it can
be called from inside the method itemStateChanged() to find out which
particular checkbox was clicked. It will return a reference to one interface
item selectable which is implemented by the class Checkbox.
- getStateChange(): This method is also defined in the class ItemEvent it
can be called to find out whether it was selection or a deselection. That
recur the event. It will return an integer.
1 – selected
2 – deselected
Example: Write a program to display a suitable message to show which
checkbox was clicked and whether it was a selection or deselection.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=CheckboxExample width=300 height=200>
</applet>*/
public class CheckboxExample extends Applet implements ItemListener
{

String msg;
Checkbox cb1,cb2;
public void init()
{
cb1=new Checkbox("Bold");
cb2=new Checkbox("Italic");
add(cb1);
add(cb2);
cb1.addItemListener(this);
cb2.addItemListener(this);
}
public void itemStateChanged(ItemEvent ie)
{
repaint();
}
public void paint(Graphics g)
{
if(cb1.getState())
{
msg="You clicked on Bold, you selected it";
g.drawString(msg,5,50);
}
else
{
msg="You clicked on Bold, you deselect it";
g.drawString(msg,5,50);

}
if(cb2.getState())
{
msg="You clicked on Italic, you selected it";
g.drawString(msg,5,70);
}
else
{
msg="You clicked on Italic, you deselect it";
g.drawString(msg,5,70);
}
}
}
Output:
F:\java programs>javac CheckboxExample.java
F:\java programs>appletviewer CheckboxExample.java
Option button: These are mutually exclusive options where only one of them
can be selected at one time. To create a set of option buttons we have to create an
object of the class checkbox group and pass this object as an argument while
constructing the individual buttons which are created as objects of the class
Checkbox creating a set of 3 option buttons.
CheckboxGroup cbg=new CheckboxGroup();

Checkbox op1=new Checkbox(“Shutdown”,cbg,true);
Checkbox op2=new Checkbox(“Restart”,cbg,false);
Checkbox op3=new Checkbox(“Standby”,cbg,false);
Checkbox group methods:
- getSelectedCheckbox(): This method is called by using the checkbox
group object to determine which option button in the group is currently
selected. It will return a reference to the selected option buttons.
Ex: Checkbox op=cbg.getSelectedCheckbox();
- setSelectedCheckbox(): This method is used to select one particular
option button in the group through the program the previously selected
option button is automatically deselected.
Ex: cbg.setSelectedCheckbox(op);
// Write a program to demonstrate option buttons
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*<applet code=OptionDemo width=500 height=300>
</applet>*/
public class OptionDemo extends Applet implements ItemListener
{
String msg;
Checkbox op1,op2,op3;
CheckboxGroup cbg;
public void init()
{
cbg=new CheckboxGroup();
op1=new Checkbox("Small",true,cbg);
op2=new Checkbox("Medium",false,cbg);
op3=new Checkbox("Large",false,cbg);
add(op1);
add(op2);

add(op3);
op1.addItemListener(this);
op2.addItemListener(this);
op3.addItemListener(this);
}
public void itemStateChanged(ItemEvent ie)
{
repaint();
}
public void paint(Graphics g)
{
msg="Current State:";
g.drawString(msg,6,80);
msg=op1.getLabel()+":"+op1.getState();
g.drawString(msg,6,100);
msg=op2.getLabel()+":"+op2.getState();
g.drawString(msg,6,120);
msg=op3.getLabel()+":"+op3.getState();
g.drawString(msg,6,140);
}
}
Output:
F:\java programs>javac OptionDemo.java
F:\java programs>appletviewer OptionDemo.java

Combo box: The class Choice is used to create a dropdown list of items in the
normal state; only the currently selected item is shown when the user clicks on
the down arrow. The list of items will drop down from which a new selection
can be made. Each item in the list is a string that appears left justified in the
order in which it is added to the choice object.
Choice ch=new Choice();
Adding items to the object:
ch.addItem(str);
Getting currently selected items: String s=ch.getSelectedItem();
This will return the string containing the name of the currently selected item.
int k=ch.getSelectedIndex();
This will return the index positions of the selected items (counting starts from
zero).
Finding number of items in the list:
int n=ch.getItemCount();
selecting an item through code.
ch.select(i); // selects the item at index position
ch.select(s) // selects the item whose name matches with s
To find the item at a particular position.
String s=ch.getItem(k); // where k is of type int
Handling choice list: Each time an item in the list is clicked an item event is

generated. So, the listener must implement the ItemListener interface which
defines the method itemStateChanged().
// Write a program to demonstrate a Combobox.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=ChoiceDemo.class width=500 height=300>
</applet>*/
public class ChoiceDemo extends Applet implements ItemListener
{
Choice language;
String msg;
public void init()
{
msg=" ";
language=new Choice();
language.addItem("C Programming");
language.addItem("C++ Programming");
language.addItem("Java Programming");
language.addItem("C# Programming");
add(language);
language.addItemListener(this);
}
public void itemStateChanged(ItemEvent ie)
{
repaint();
}
public void paint(Graphics g)
{

msg="Currently selected programming language: ";
msg+=language.getSelectedItem();
g.drawString(msg,6,140);
}
}
Output:
F:\java programs>javac ChoiceDemo.java
F:\java programs>appletviewer ChoiceDemo.java
List boxes: The class List provides a scrolling selection list unlike a choice object
which only shows the currently selected items and it can also allow multiple
selection.
Creating a List box:
- List l1=new List() this creates a single selection list.
- List l2=new List(n) specifies the number of items that will be visible at a
time in the list box; others will have to be scrolled to view as needed.
- List l3=new List(n,b) if b is true, it allows multiple selection.
Methods for single selection list:
- getSelectedItem(): Returns the string containing the name of the currently
selected items suppose, no items is selected it returns null.
Ex: String s=l1.getSelectedItem();
Adding item to the list:

l1.addItem(str);
l1.addItem(str,k);
- getSelectedIndex(): Returns the position of the selected items if no items
have been selected it will return -1
ex: int k=l1.getSelectedIndex();
methods for multiple selection list:
- getSelectedItems(): Returns an array of strings containing the names of
the currently selected items.
String s[]=l3.getSelectedItems();
- getSelectedIndex(): This will return an array of int containing the
positions of the currently selected items.
Ex: int ind[]=l3.getSelectedIndex();
Note: To find out how many items have been selected we can use the expression
either s.length or ind.length.
Common methods:
- getItemCount(): Returns the total number of items in the list.
int m=l1.getItemCount();
- getItem(): Returns the name of the item at a particular position.
ex: String s=l1.getItem(k);
Handling list: Each time a list item is clicked i.e. single click, an item event is
generated each time a list item is double clicked and action event is generated. In
order to respond to both the listeners we must implement the ActionListener and
ItemListener interface which contain the methods itemStateChanged() and
actionPerformed() respectively.
// Write a program to demonstrate a list box.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=ListDemo.class width=500 height=300>
</applet>*/
public class ListDemo extends Applet implements ActionListener
{
List language;

String msg="";
public void init()
{
language=new List(4,false);
language.add("C Programming");
language.add("C++ Programming");
language.add("Java Programming");
language.add("C# Programming");
language.select(0);
add(language);
language.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
repaint();
}
public void paint(Graphics g)
{
int s[];
s=language.getSelectedIndexes();
for(int i=0;i<s.length;i++)
msg+=language.getItem(s[i])+" ";
g.drawString(msg,6,140);
}
}
Output:
F:\java programs>javac ListDemo.java

F:\java programs>appletviewer ListDemo.java
Note: Double click on list items to display the name of the selected item.
// Write a program to delete items from the list box.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=TestDemo.class width=500 height=300>
</applet>*/
public class TestDemo extends Applet implements ActionListener
{
Button b1;
List l1;
String msg;
public void init()
{
msg=" ";
b1=new Button("Delete");
l1=new List();
l1.addItem("Doctor");
l1.addItem("Engineer");

l1.addItem("Scientist");
add(b1);
add(l1);
b1.addActionListener(this);
l1.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
Object obj=ae.getSource();
if(obj==b1)
{
msg="Button";
l1.delItem(0);
}
else
msg="List box";
repaint();
}
public void paint(Graphics g)
{
g.drawString(msg,6,140);
}
}
Output:
F:\java programs>javac TestDemo.java
F:\java programs>appletviewer TestDemo.java

Adapter classes: Java provides a special feature called adapter classes and
adapter class provides empty implementation of all the methods in an
EventListener interface. This becomes useful when you want to process only
some of the methods that are handled by a particular interface.
We can define a new class to act as an event listener by extending the
appropriate adapter class and overriding only those methods which we are
interested in.
For example: the mouse adapter class which is defined in the java.awt.events
packages has five methods.
- mousePressed()
- mouseReleased()
- mouseClicked()
- mouseExited()
- mouseEntered()
The signature of all these methods are exactly as defined in the mouse listener
interface. So, if we were interested in only responding to the mouseClicked()
method. Then we can extend the mouse adapter class and write the code only for
the mouseClicked() method.
Names of adapter class Listener Interface it implements
KeyAdapter KeyListener
MouseAdapter MouseListener
MouseMotionAdapter WindowListener
FocusAdapter FocusListener
// Write a program to illustrate the use of the adapter class.

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=AdapterDemo.class width=300 height=200>
</applet>*/
public class AdapterDemo extends Applet
{
String msg;
public void init()
{
msg=" ";
xyz m1=new xyz(this);
addMouseListener(m1);
}
public void paint(Graphics g)
{
g.drawString(msg,10,100);
}
}
class xyz extends MouseAdapter
{
AdapterDemo ad;
xyz(AdapterDemo ob)
{
ad=ob;
}
public void mouseClicked(MouseEvent me)
{
ad.showStatus("Mouse was clicked");

ad.msg="You clicked the mouse";
ad.repaint();
}
}
Output:
F:\java programs>javac AdapterDemo.java
F:\java programs>appletviewer AdapterDemo.java
Note: The above program responds only to the clicking of the mouse; the other
four mouse events are ignored. Entry of the mouse, its exit, pressing and
releasing of the mouse.
Frame classes:
A standard child window can
be created by defining a class that is derived from a frame. By creating an object

of such a class we can display a standard window with title bar, menu bar,
borders resizing corners and minimum and maximum and close buttons. The
warning alerts to the user can be given using the child window can be given that,
this child window was started by an applet program and not by a local program
on this computer so the user should be careful about giving sensitive information
through this window.
Constructors:
SampleFrame sf=new SampleFrame();
SampleFrame sf1=new SampleFrame(str);
- setSize(): sf.setSize(w,h);
- setVisible(): sf.setVisible(true); // shows the child window
setVisible(): sf.setVisible(false);
- setTitle(): sf.setTitle(str);
// Write a program to illustrate frame class.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=FrameDemo.class width=500 height=200>
</applet>*/
public class FrameDemo extends Applet
{
SampleFrame sf;
public void init()
{
sf=new SampleFrame("A child window");
sf.setSize(200,200);
}
public void start()
{
sf.setVisible(true);
}

public void stop()
{
sf.setVisible(false);
}
public void paint(Graphics g)
{
g.drawString("This is an applet",10,100);
}
}
class SampleFrame extends Frame
{
SampleFrame(String title)
{
super(title);
xyz adp=new xyz(this);
addWindowListener(adp);
}
public void paint(Graphics g)
{
g.drawString("This is a child window",10,100);
}
}
class xyz extends WindowAdapter
{
SampleFrame sf;
xyz(SampleFrame ob)
{
sf=ob;
}

}
Output:
F:\java programs>javac FrameDemo.java
F:\java programs>appletviewer FrameDemo.java
Designing menus: The following classes are used in designing the menu
- Menubar
- Menu
- MenuItem
- CheckboxMenuItem
A menu bar is an object of the class menu bar. It displays the list of top level
menu titles each title is an object of the class menu and has a dropdown list
which may contain any or all of the following.
- MenuItem objects: Represents an option which the user can click.
- Menu object: As a right pointing arrow leading to a submenu.
- CheckboxMenuItem objects: Represents a checkable menu item. These
options will show a check mark when selected.
Creating a menu bar object:
MenuBar mb=new MenuBar();
Creating a menu object:
Menu f=new Menu(“File”);
Creating a MenuItem object:
MenuItem i1=new MenuItem(“Cut”);
MenuItem i2=new MenuItem(str,key);

Creating a checkbox MenuItem objects:
CheckboxMenuItem cb1=new CheckboxMenuItem(“Bold”,true);
CheckboxMenuItem cb2=new CheckboxMenuItem(“Italic”);
MenuItem methods:
- setEnabled(): This method is used to either enable or disable a menu item.
i1.setEnabled(b); // this method receives Boolean value
- isEnabled(): This method determines if a particular menu item is enabled
or disabled.
boolean b=i1.isEnabled();
Checkbox menu item methods:
- getState(): Returns the state of the option true if it is selected otherwise
false.
boolean b=cb1.getState();
- setState(): This method sets the state of the option through the program.
cb1.setState(b);
Adding options to a menu object:
f.add(obj);
Adding menu objects to the menu bar object:
mb.add(f);
Adding menu bar object to the frame window:
this.setMenuBar(mb);
Handling menus: Events are generated only when a MenuItem or a
CheckboxMenuItem is clicked. No event is generated when a menu object is
clicked to display the dropdown list.
MenuItem generates ActionEvent. CheckboxMenuItem generates ItemEvent. So,
the listener class must implement both ActionListener as well as ItemListener
interface in order to respond to both types of events.
// Write a program to demonstrate menus.
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*<applet code=MenuDemo.class width=300 height=200>

</applet>*/
public class MenuDemo extends Applet
{
SampleFrame sf;
public void init()
{
sf=new SampleFrame("SampleFrame");
sf.setSize(200,180);
}
public void start()
{
sf.setVisible(true);
}
public void stop()
{
sf.setVisible(false);
}
}
class SampleFrame extends Frame implements ActionListener
{
int flag;
SampleFrame(String title)
{
super(title);
Menu m1=new Menu("Main");
Menu sub=new Menu("Draw") ;
MenuItem i1=new MenuItem("Rectangle");
MenuItem i2=new MenuItem("Ellipse");
MenuItem i3=new MenuItem("Line");

sub.add(i1);
sub.add(i2);
sub.add(i3);
m1.add(sub);
MenuBar mb=new MenuBar();
mb.add(m1);
this.setMenuBar(mb);
i1.addActionListener(this);
i2.addActionListener(this);
i3.addActionListener(this);
MyWindowAdapter adp=new MyWindowAdapter(this);
this.addWindowListener(adp);
}
public void actionPerformed(ActionEvent ae)
{
String arg=ae.getActionCommand();
if(arg.equals("Rectangle"))
flag=1;
else
if(arg.equals("Ellipse"))
flag=2;
else
flag=3;
repaint();
}
public void paint(Graphics g)
{
switch(flag)
{

case 1: g.drawRect(10,50,100,80);
break;
case 2: g.drawOval(10,50,100,80);
break;
case 3: g.drawLine(10,50,100,80);
break;
}
}
}
class MyWindowAdapter extends WindowAdapter
{
SampleFrame sf;
public MyWindowAdapter(SampleFrame ob)
{
sf=ob;
}
public void windowClosing(WindowEvent we)
{
sf.setVisible(false);
}
}
Output:
F:\java programs>javac MenuDemo.java
F:\java programs>appletviewer MenuDemo.java

Popup menus:
// Write a program to display a popup menu.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=PopupDemo.class width=300 height=200>
</applet>*/
public class PopupDemo extends Applet implements ActionListener
{
TextField t1;
PopupMenu p;
MenuItem i1,i2,i3;
public void init()
{
p=new PopupMenu("Edit");
i1=new MenuItem("Cut");
i2=new MenuItem("Copy");
i3=new MenuItem("Paste");
p.add(i1);
p.add(i2);
p.addSeparator();
p.add(i3);

add(p);
t1=new TextField(20);
add(t1);
i1.addActionListener(this);
i2.addActionListener(this);
i3.addActionListener(this);
}
public void mousePressed(MouseEvent me)
{
if(me.getModifiers()==4)
p.show(this,me.getX(),me.getY());
}
public void mouseClicked(MouseEvent me)
{}
public void mouseReleased(MouseEvent me)
{}
public void Exited(MouseEvent me)
{}
public void Entered(MouseEvent me)
{}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==i1)
t1.setText("Cut option was clicked");
if(ae.getSource()==i2)
t1.setText("Copy option was clicked");
if(ae.getSource()==i3)
t1.setText("Paste option was clicked");
}

}
Output:
F:\java programs>javac PopupDemo.java
F:\java programs>appletviewer PopupDemo.java
Dialog box: A dialog box is used to obtain further user input before a command
is to be executed. It is similar to a frame window but will not have a menu bar
with minimized or maximized buttons.
A set of related controls can be added to a dialog box such as label list box,
buttons etc. A dialog box can either be modal or modeless. If it is a model then
you will not be able to shift the focus to some other window until you have
closed this dialog box. A dialog box can be created by first defining a class that
is derived from dialog.
By creating an object of such a class, we can create a dialog box.
- Dialog(frame,boolean);
- Dialog(frame,string,boolean);
// Write a program to illustrate a dialog box.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*<applet code=Dialogbox.class width=500 height=200>
</applet>*/
public class Dialogbox extends Applet
{
SampleFrame f;
public void init()
{
f=new SampleFrame("Menu Demo");
f.setSize(250,250);
}
public void start()
{
f.setVisible(true);
}
public void stop()
{
f.setVisible(false);
}
}
class SampleFrame extends Frame implements ActionListener
{
String msg;
SampleFrame(String title)
{
super(title);
msg=" ";
MenuBar mb=new MenuBar();
Menu f=new Menu("File");
MenuItem i1=new MenuItem("Open...");

f.add(i1);
mb.add(f);
setMenuBar(mb);
i1.addActionListener(this);
MyWindowAdapter adp=new MyWindowAdapter(this);
addWindowListener(adp);
}
public void actionPerformed(ActionEvent ae)
{
msg="You selected";
String arg=ae.getActionCommand();
if(arg.equals("Open..."))
{
msg+="Open";
SampleDialog db=new SampleDialog(this,"File open box");
db.setVisible(true);
}
repaint();
}
public void paint(Graphics g)
{
g.drawString(msg,10,200);
}
}
class MyWindowAdapter extends WindowAdapter
{
SampleFrame sf;
public MyWindowAdapter(SampleFrame sf)
{

this.sf=sf;
}
public void windowClosing(WindowEvent we)
{
sf.setVisible(false);
}
}
class SampleDialog extends Dialog implements ActionListener
{
SampleDialog(Frame parent, String title)
{
super(parent,title,false);
setLayout(new FlowLayout());
setSize(300,200);
add(new Label("Check this button"));
Button b=new Button("Cancel");
add(b);
b.addActionListener(this);
}
public void actionPerformed(ActionEvent ae)
{
dispose();
}
public void paint(Graphics g)
{
g.drawString("This is a dialog box",10,70);
}
}
Output:

F:\java programs>javac Dialogbox.java
F:\java programs>appletviewer Dialogbox.java

Images: We can display images on the applets such as .jpg.gif or .bmp by first
creating an object of the class image which is defined in the java.awt.package.
Loading an image:
getImage(URL, str);
where, URL is Uniform Resource Locator and str is name of the file (.jpg, gif,
.bmp)
Displaying the image: Once an image has been loaded, you can display inside
the applet window by calling the method.
drawInage() which is defined in the class Graphics.
g.drawImage(img,x,y,obj);
// Write a program to illustrate the image.
import java.awt.*;
import java.applet.*;
/*<applet code=ImageDemo.class width=300 height=350>
<param name="picture" value="Hanumanth Ladwa.jpg"></applet>*/
public class ImageDemo extends Applet
{
Image img;
public void init()
{
img=getImage(getDocumentBase(),getParameter("picture"));
}
public void paint(Graphics g)
{
g.drawImage(img,0,0,this);
g.drawString("My Name Is Hanumanth Ladwa",65,320);

}
}
Output:
F:\java programs>javac ImageDemo.java
F:\java programs>appletviewer ImageDemo.java
getDocumentBase(): It is a predefined method which returns the location in
which the HTML file which started this applet is stored. This location is returned
as a URL object.
URL u=getDocumentBase();
EXERCISES
1. What do you mean by event handling? explain the components of it.
2. Write a program to demonstrate mouse event handlers.
3. Explain all user interface components.
4. Write a program to demonstrate a checkbox.
5. Explain the methods for the single selection list.
6. Write a program to demonstrate a list box.
7. Write a program to delete items from the list box.
8. Write a program to illustrate the use of the adapter class.
9. Explain how to design menus?
10. Write a program to handle menus.

11. Write a program to illustrate a popup menu.
12. Write a program to display a dialog box.
13. Explain how to display images with a programming example.

CHAPTER 15
SWING
Introduction: swing is a part of the Java Foundation Classes (or JFC). It is
basically a set of classes that provides more powerful and flexible components
then those in the awt. It has an enhanced version of the standard awt controls
such as labels, buttons, checkboxes etc.
All the swing related classes are contained in the javax.swing package.
JApplet class: All applet programs which use the swing classes must extend
JApplet which is a subclass of applets.
Icons: In swing programs icons are created as objects of the class ImageIcon.
ImageIcon i1=new ImageIcon(“file name”);
The class ImageIcon implements one interface called Icon.
JLabel class: swing labels are instances of the JLabel class which can display
both text as well as icon.
Constructors: JLabel(Icon i);
JLabel(String s);
Jlabel(String s,Icon I, int align)
Align: it could be any of the following constants which are defined in one
interface.
javax.swing constants
LEFT
RIGHT
CENTER
LEADING
TRAILING
Methods for JLabel class:
1. void setIcon(Icon i);
2. void setText(String s);
Example:

import java.awt.*;
import javax.swing.*;
/*<applet code=JLabelDemo.class width=500 height=300>
</applet>*/
public class JLabelDemo extends JApplet
{
public void init()
{
Container cp=getContentPane();
cp.setLayout(new FlowLayout(1));
ImageIcon i1=new ImageIcon("TajMahal.JFIF");
JLabel l1=new JLabel("Taj Mahal", i1,JLabel.CENTER);
cp.add(l1);
}
}
Output:
F:\java programs>javac JLabelDemo.java
F:\java programs>appletviewer JLabelDemo.java
getContentPane(): Returns the reference to a container object when adding a

component in the swing program, the container object (cp) should be used to
involve the add method.
JTextField class: It is used to create textboxes.
Constructor: JTextField(String s, int n);
JButton class: To create command buttons.
Constructors: JButton(Icon i);
JButton(String s);
JButton(String s, Icon i);
// Write a program to illustrate JButton.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*<applet code=JButtonDemo.class width=500 height=300>
</applet>*/
public class JButtonDemo extends JApplet implements ActionListener
{
JTextField t1;
public void init()
{
Container cp=getContentPane();
cp.setLayout(new FlowLayout());
ImageIcon i1=new ImageIcon("C Programming.jpg");
JButton b1=new JButton(i1);
b1.setActionCommand("C Programming");
b1.addActionListener(this);
cp.add(b1);
ImageIcon i2=new ImageIcon("C++ Programming.jpg");

JButton b2=new JButton(i2);
b2.setActionCommand("C++ Programming");
b2.addActionListener(this);
cp.add(b2);
t1=new JTextField(15);
cp.add(t1);
}
public void actionPerformed(ActionEvent ae)
{
String s=ae.getActionCommand();
t1.setText(s);
}
}
Output:
F:\java programs>javac JButtonDemo.java
F:\java programs>appletviewer JButtonDemo.java
The above program shows two command buttons and one text field. Each button
displays a different country’s flag whenever a button is clicked. A name of the

country will be displayed inside the text field.
JCheckbox class: It is used for creating checkboxes.
Constructors:
JCheckbox(Icon i);
JCheckbox(Icon i,Boolean b);
JCheckbox(String s);
JCheckbox(String s, boolean b);
JCheckbox(String s, Icon i);
JCheckbox(String s, Icon I, boolean b);
Methods:
- setRollOverIcon(): It specifies the icon to be displayed whenever the
mouse is moved over the checkbox at runtime.
cb.setRollOverIcon(i1);
- setSelectedIcon(): Specifies the icon to be displayed when the checkbox
is in the selected state.
cb.setSelectedIcon(i2);
// Write a program to illustrate the swing checkbox.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*<applet code=JCheckboxDemo width=300 height=200>
</applet>*/
public class JCheckboxDemo extends JApplet implements ItemListener
{
JTextField t1;
public void init()
{
Container cp=getContentPane();
cp.setLayout(new FlowLayout());

ImageIcon n=new ImageIcon("normal.jpg");
ImageIcon r=new ImageIcon("rollover.jpg");
ImageIcon s=new ImageIcon("selected.jpg");
JCheckBox cb=new JCheckBox("C",n);
cb.setRolloverIcon(r);
cb.setRolloverIcon(s);
cb.addItemListener(this);
cp.add(cb);
cb=new JCheckBox("C++",r);
cb.setRolloverIcon(r);
cb.setRolloverIcon(s);
cb.addItemListener(this);
cp.add(cb);
cb=new JCheckBox("Java",s);
cb.setRolloverIcon(r);
cb.setRolloverIcon(s);
cb.addItemListener(this);
cp.add(cb);
t1=new JTextField(15);
cp.add(t1);
}
public void itemStateChanged(ItemEvent ie)
{
JCheckBox cb=(JCheckBox) ie.getItem();
t1.setText(cb.getText());

}
}
Output:
F:\java programs>javac JCheckboxDemo.java
F:\java programs>appletviewer JcheckboxDemo.java
JRadioButton class: For creating option buttons we use JRadioButton class.
constructors are similar to the class JCheckbox. The additional requirement is
that option buttons have to be members of a group. For this we have to create an
object of a class ButtonGroup and the individual option buttons have to be added
to this group.
// Write a program to demonstrate JRadioButton.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*<applet code=JRadioButtonDemo.class width=300 height=200>
</applet>*/
public class JRadioButtonDemo extends JApplet implements ActionListener
{
JTextField t1;
public void init()
{
Container cp=getContentPane();

cp.setLayout(new FlowLayout());
JRadioButton b1=new JRadioButton("Windows");
b1.addActionListener(this);
cp.add(b1);
JRadioButton b2=new JRadioButton("Unix");
b2.addActionListener(this);
cp.add(b2);
JRadioButton b3=new JRadioButton("Linux");
b3.addActionListener(this);
cp.add(b3);
ButtonGroup bg=new ButtonGroup();
bg.add(b1);
bg.add(b2);
bg.add(b3);
t1=new JTextField(15);
cp.add(t1);
}
public void actionPerformed(ActionEvent ae)
{
t1.setText(ae.getActionCommand());
}
}
Output:
F:\java programs>javac JRadioButtonDemo.java

F:\java programs>appletviewer JRadioButtonDemo.java
EXERCISES
1. What is swing? Explain with a program to demonstrate the use of
JLabel class.
2. Write a program to illustrate JButton.
3. Write a program to illustrate JCheckBox.
4. Write a program to demonstrate swing checkboxes.
5. Write a program to illustrate JRadioButton class.

CHAPTER 16
FILE HANDLING
Introduction: File is an abstract data type in java. A file is a basic unit of storage
of data or information for future reference. There are various operations on files
like, creating a new file, getting information about file, writing into a file,
reading from a file and deleting a file. Java uses Stream and File methods to
perform all the above operations.
Stream: A flow of byte is referred to as a stream. Stream is classified into two
types.
- Byte stream
- Character stream
Byte stream: File handling with a byte stream is a process in which an input is
provided and executed with the byte data.

Character stream: File handling with a character stream is a process in which an
input is provided and executed with the character data.
The following table lists file class methods:
Return
Method Description
Type
The canRead() method is used to check
canRead() Boolean whether we can read the data of the file or
not.
The createNewFile() method is used to create
createNewFile() Boolean
a new empty file.
The canWrite() method is used to check
canWrite() Boolean whether we can write the data into the file or
not.
The exists() method is used to check whether
exists() Boolean
the specified file is present or not.
delete() Boolean The delete() method is used to delete a file.
The getName() method is used to find the file
getName() String
name.
The getAbsolutePath() method is used to get
getAbsolutePath() String
the absolute pathname of the file.
The length() method is used to get the size of
length() Long
the file in bytes.
The list() method is used to get an array of the
list() String[]
files available in the directory.
The mkdir() method is used for creating a
mkdir() Boolean
new directory.
File operations:
We can perform the following operations on a file:
- Create a file
- Get a file information
- Write to a file
- Read from a file
- Delete a file
Create a file: We use the createNewFile() method to create a new file. It returns
true when the file is successfully created and returns false when the file already

exists.
// Write a program to create a file.
import java.io.File; // Import the File class
import java.io.IOException; // Import the IOException class to handle errors
public class CreateFileDemo
{
public static void main(String[] args)
{
try
{
File myObj = new File("DemoFile.txt");
if (myObj.createNewFile())
{
System.out.println("File created: " + myObj.getName());
}
else
{
System.out.println("File already exists.");
}
}
catch (IOException e)
{
System.out.println("An error occurred.");
e.printStackTrace();
}
}
}
Output:

F:\java programs>javac CreateFileDemo.java
F:\java programs>java CreateFileDemo
File created: DemoFile.txt
Upon execution of this program, DemoFile.txt is created in the folder in which
the java program is saved. If you want to change the place of the file to be
created then you use the specific path by using backslash (“\”).
Get file information: This is the operation that gets the file information such as
file name, absolute path, is readable, is writable and length.
// Write a program to get file information.
// Import the File class
import java.io.File;
class FileInfo
{
public static void main(String[] args)
{
File f0 = new File("DemoFile.txt");
if (f0.exists())
{
System.out.println("The name of the file is: " + f0.getName());
System.out.println("The absolute path of the file is: " +
f0.getAbsolutePath());

System.out.println("Is file writable?: " + f0.canWrite());
System.out.println("Is file readable " + f0.canRead());
System.out.println("The size of the file in bytes is: " + f0.length());
}
else
{
System.out.println("The file does not exist.");
}
}
}
Output:
F:\java programs>javac FileInfo.java
F:\java programs>java FileInfo
The name of the file is: DemoFile.txt
The absolute path of the file is: F:\java programs\DemoFile.txt
Is file writable?: true
Is file readable true
The size of the file in bytes is: 0
Write to a file:
FileWriter class and write() method together used to write to a file. Once the
write operation is over, the opened file has to close by using the close() method.
// Write a program to write text to a file.
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
public class WriteToFile
{
public static void main(String[] args)

{
try
{
FileWriter myWriter = new FileWriter("DemoFile.txt");
myWriter.write("My first love of life is programming");
myWriter.close();
System.out.println("Successfully wrote to the file.");
}
catch (IOException e)
{
System.out.println("An error occurred.");
e.printStackTrace();
}
}
}
Output:
F:\java programs>javac WriteToFile.java
F:\java programs>java WriteToFile
Successfully wrote to the file.
Read from a file: To read from a file we use a class called Scanner and the
opened file should be closed using a method called close() . To get the data from

the file we use hasNextLine() method nextLine() method.
// Write a program to read data from a file.
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class ReadFile
{
public static void main(String[] args)
{
try
{
File myObj = new File("DemoFile.txt");
Scanner myReader = new Scanner(myObj);
while (myReader.hasNextLine()) {
String data = myReader.nextLine();
System.out.println(data);
}
myReader.close();
}
catch (FileNotFoundException e)
{
System.out.println("An error occurred.");
e.printStackTrace();
}
}
}
Output:
F:\java programs>javac ReadFile.java

F:\java programs>java ReadFile
My first love of life is programming
Note: There are many classes available in the Java API that can be used to read
and write files in java like, FileReader , BufferedReader , Files , Scanner ,
FileInputStream , FileWriter , BufferedWriter , FileOutputStream and so on.
Delete a file: To delete a file in Java, use the delete() method.
// Write a program to delete a file.
import java.io.File; // Import the File class
public class DeleteFile
{
public static void main(String[] args)
{
File myObj = new File("DemoFile.txt");
if (myObj.delete())
{
System.out.println("Deleted the file: " + myObj.getName());
}
else
{
System.out.println("Failed to delete the file.");
}
}
}
Output:
F:\java programs>javac DeleteFile.java
F:\java programs>java DeleteFile

Deleted the file: DemoFile.txt
EXERCISES
1. Explain file class methods.
2. Describe to create a file with a programming example.
3. Write a program to get file information.
4. How to write a file? explain with suitable examples.
5. Explain the process of reading a file with an example.
6. Describe to delete a file with a programming example.

LAB ASSIGNMENTS
/*1. Write a program to print the list of prime numbers up to the limit
entered. */
import java.util.Scanner;
class PrimeNumbers
{
public static void main(String[] args)
{
try
{
System.out.println("***** PRIME NUMBERS *****");
Scanner objScanner = new Scanner(System.in);
System.out.print("\n Enter n Value:");
long n = objScanner.nextInt();
for (long i = 2; i <= n; i++)
{
boolean isprime = isNumPrime(i);
if (isprime)
{
System.out.print(i + " ");
}
}
}
catch (Exception e)
{
e.printStackTrace();
}
}

public static boolean isNumPrime(long number)
{
boolean result = true;
for (long i = 2; i <= number / 2; i++)
{
if ((number % i) != 0)
{
result = true;
}
else
{
result = false;
break;
}
}
return result;
}
}
Output:
F:\java programs>javac PrimeNumbers.java
F:\java programs>java PrimeNumbers
***** PRIME NUMBERS *****
Enter n Value:10
2 3 5 7

//2. Write a program to print Fibonacci series.
class FibonacciSeries
{
static int n1=0,n2=1,n3=0;
static void printFibonacci(int count)
{
if(count>0)
{
n3 = n1 + n2;
n1 = n2;
n2 = n3;
System.out.print(" "+n3);
printFibonacci(count-1);
}
}
public static void main(String args[])
{
int count=10;
System.out.print(n1+" "+n2);
printFibonacci(count-2);
}
}
Output:
F:\java programs>javac FibonacciSeries.java
F:\java programs>java FibonacciSeries
0 1 1 2 3 5 8 13 21 34

//3. Write a Java Program to find the average of n numbers in an array.
class Avg
{
public static void main(String args[])
{
int n=args.length;
float [] x=new float[n];
for(int i=0; i<n; i++)
{
x[i]=Float.parseFloat(args[i]);
}
float sum=0;
for(int i=0; i<n; i++)
sum=sum+x[i];
float avg=sum/n;
System.out.println("Average of given numbers is "+avg);
}
}
Output:
F:\java programs>javac Avg.java
F:\java programs>java Avg 1 2 3 4 5 6
Average of given numbers is 3.5

/*4. Write a java program in which threads sleep for 5 sec and change the
name of the thread. */
import java.lang.*;
class ThreadTesting extends Thread
{
static
{
Thread t = Thread.currentThread();
System.out.println("thread test is loaded by "+t.getName()+" thread");
t.setName("Hanumanth");
System.out.println("changed the name of thread");
System.out.println("suspending thread for 5 sec");
try
{
Thread.sleep(5000);
}
catch(Exception ex){}
}
public static void main(String arr[])
{
Thread t=Thread.currentThread();
System.out.println("main() is invoked in "+t.getName()+" thread...");
}
}
Output:
F:\java programs>javac ThreadTesting.java
F:\java programs>java ThreadTesting

thread test is loaded by main thread
changed the name of thread
suspending thread for 5 sec
main() is invoked in the Hanumanth thread...
/*5. Write a java program to solve producer consumer problems in which a
producer produces a value and consumer consumes the value before
producer generates the next value. */
class Buffer
{
int value;
boolean produced=false;
public synchronized void produce(int x)
{
if(produced)
{
System.out.println("producer enter monitor out of turn..suspend.....");
try
{
wait();
}
catch(Exception e)
{}
}
value=x;
System.out.println(value+" is produced");
produced=true;
notify();
}

public synchronized void consume()
{
if(! produced)
{
System.out.println("consumer enters the monitor out of turn,suspend......");
try{
wait();
}catch(Exception e)
{}
}
System.out.println(value+"is consumed");
produced=false;
notify();
}
}
class Producer extends Thread
{
Buffer buffer;
public Producer(Buffer b)
{
buffer =b;
}
public void run()
{
System.out.println("producer started ,producing value........");
for(int i=1;1<=10;i++)
buffer.produce(i);
}
}

class Consumer extends Thread
{
Buffer buffer;
public Consumer(Buffer b)
{
buffer =b;
}
public void run()
{
System.out.println("consumer started,consuming value.......");
for(int i=1;i<=10;i++)
buffer.consume();
}
}
class PoducerConsumerDemo
{
public static void main(String arr[])
{
Buffer b=new Buffer();
Producer p=new Producer(b);
Consumer c=new Consumer(b);
p.start();
c.start();
}
}
Output:
F:\java programs>javac PoducerConsumerDemo.java
F:\java programs>java PoducerConsumerDemo

producer started ,producing value........
consumer started,consuming value.......
1 is produced
producer enters monitor out of turn..suspend.....
1is consumed
consumer enters the monitor out of turn,suspends......
2 is produced
producer enters monitor out of turn..suspend.....
2is consumed
consumer enters the monitor out of turn,suspends......
3 is produced
producer enters monitor out of turn..suspend.....
3is consumed
consumer enters the monitor out of turn,suspends......
4 is produced
producer enters monitor out of turn..suspend.....
4is consumed
consumer enters the monitor out of turn,suspends......
5 is produced
producer enters monitor out of turn..suspend.....
5is consumed
consumer enters the monitor out of turn,suspends......
6 is produced
producer enters monitor out of turn..suspend.....
6is consumed
consumer enters the monitor out of turn,suspends......
7 is produced
producer enters monitor out of turn..suspend.....
7is consumed

consumer enters the monitor out of turn,suspends......
8 is produced
producer enters monitor out of turn..suspend.....
8is consumed
consumer enters the monitor out of turn,suspends......
9 is produced
producer enters monitor out of turn..suspend.....
9is consumed
consumer enters the monitor out of turn,suspends......
10 is produced
producer enters monitor out of turn..suspend.....
10is consumed
11 is produced
producer enters monitor out of turn..suspend.....
/*6. Write a java program to create a file and write the text in it and save
the file. */
import java.io.*;
class CreateFile
{
public static void main(String arr[])
{
if(arr.length<1)
{
System.out.println("usage:javacreatefile file name");
System.exit(0);
}
try
{
BufferedReader b=new BufferedReader(new InputStreamReader(System.in));

PrintStream fos=new PrintStream(new FileOutputStream(arr[0]));
System.out.println("Enter text end to save");
PrintStream temp=System.out;
System.setOut(fos);
do
{
String str=b.readLine();
if(str.equalsIgnoreCase("end"));
System.out.println(str);
break;
}while(true);
System.setOut(temp);
fos.close();
b.close();
System.out.println("successfully created");
}
catch(Exception ex)
{
System.out.println(ex);
}
}
}
Output:
F:\java programs>javac CreateFile.java
F:\java programs>java CreateFile Hanumanth Ladwa
Enter text end to save
end
successfully created

/*7. Write a java program that checks whether a given string is palindrome
or not. */
class Palindrome
{
public static void main(String args[])
{
String s=args[0];
String s1=""; int l,i;
l=s.length();
// Loop to find the reverse of the string.
for(i=l-1;i>=0;i--)
{
s1=s1+s.charAt(i);
}
// Condition to find whether two strings are equal // and display the message.
if(s.equals(s1))
System.out.println("String "+s+" is palindrome");
else
System.out.println("String "+s+" is not palindrome");
}
}
Output:
F:\java programs>javac Palindrome.java
F:\java programs>java Palindrome Hanumanth

String Hanumanth is not palindrome
F:\java programs>java Palindrome madam
String madam is palindrome
/*8. Write a java program that implements a multithreaded application that
has three threads. First thread generates a random integer every 1 second
and if the value is even, the second thread computes the square of the
numbers and prints. If the value is odd the third thread will print the value
of the cube of the number. */
import java.util.Random;
class Thread1 extends Thread
{
int randnum;
public void generateRand()
{
Random rand = new Random();
for(int i=1;i<=10;i++)
{
randnum=rand.nextInt(20);
System.out.println("Random Number: "+randnum);
if(randnum%2==0)
{
Thread t2=new Thread(new Thread2(randnum));
t2.start();
}
else
{
Thread t3=new Thread(new Thread3(randnum));
t3.start();

}
try
{
Thread.sleep(1000);
}
catch(Exception e)
{
System.out.println(e);
}
}
}
public void run()
{
generateRand();
}
};
class Thread2 extends Thread1 implements Runnable
{
int randnum;
Thread2(int randnum)
{
this.randnum=randnum;
}
void printSquare()
{
System.out.println("Thread-2: Square of rand number is:"+randnum*randnum);
}
public void run()
{

printSquare();
}
}
class Thread3 extends Thread1 implements Runnable
{
int randnum;
Thread3(int randnum)
{
this.randnum=randnum;
}
void printCube()
{
System.out.println("Thread-3: Cube of rand number is: "+
randnum*randnum*randnum);
}
public void run()
{
printCube();
}
}
class MultiThread
{
public static void main(String[] args)
{
Thread1 t1=new Thread1();
t1.start();
}
}
Output:

F:\java programs>javac MultiThread.java
F:\java programs>java MultiThread
Random Number: 8
Thread-2: Square of rand number is:64
Random Number: 1
Thread-3: Cube of rand number is: 1
Random Number: 7
Thread-3: Cube of rand number is: 343
Random Number: 14
Thread-2: Square of rand number is:196
Random Number: 6
Thread-2: Square of rand number is:36
Random Number: 12
Thread-2: Square of rand number is:144
Random Number: 3
Thread-3: Cube of rand number is: 27
Random Number: 18
Thread-2: Square of rand number is:324
Random Number: 17
Thread-3: Cube of rand number is: 4913
Random Number: 7
Thread-3: Cube of rand number is: 343
/*9. Write a java program to create an abstract class that illustrates
different geometrical figures. */
abstract class shape
{
public int x, y;

public abstract void printArea();
}
class Rectangle extends shape
{
public void printArea()
{
System.out.println("Area of Rectangle is " + x * y);
}
}
class Triangle extends shape
{
public void printArea()
{
System.out.println("Area of Triangle is " + (x * y) / 2);
}
}
class Circle extends shape
{
public void printArea()
{
System.out.println("Area of Circle is " + (22 * x * x) / 7);
}
}
public class AbstractExample
{
public static void main(String[] args)
{
Rectangle r = new Rectangle();
r.x = 5;

r.y = 10;
r.printArea();
System.out.println(" -------------------------------------");
Triangle t = new Triangle();
t.x = 10;
t.y = 15;
t.printArea();
System.out.println(" -------------------------------------");
Circle c = new Circle();
c.x = 2;
c.printArea();
System.out.println(" -------------------------------------");
}
}
Output:
F:\java programs>javac AbstractExample.java
F:\java programs>java AbstractExample
Area of Rectangle is 50
-------------------------------------
Area of Triangle is 75
-------------------------------------
Area of Circle is 12
-------------------------------------
//10. java program to implements bubble sort algorithm
import java.util.Scanner;
class BubbleSort {
public static void main(String []args) {

int num, i, j, temp;
Scanner input = new Scanner(System.in);
System.out.println("Enter the number of integers to sort:");
num = input.nextInt();
int array[] = new int[num];
System.out.println("Enter " + num + " integers: ");
for (i = 0; i < num; i++)
array[i] = input.nextInt();
for (i = 0; i < ( num - 1 ); i++) {
for (j = 0; j < num - i - 1; j++) {
if (array[j] < array[j+1])
{
temp = array[j];
array[j] = array[j+1];
array[j+1] = temp;
}
}
}
System.out.println("Sorted list of integers:");
for (i = 0; i < num; i++)
System.out.println(array[i]);
}
}
Output:
F:\java programs>javac BubbleSort.java
F:\java programs>java BubbleSort
Enter the number of integers to sort:
4

Enter 4 integers:
30
10
40
20
Sorted list of integers:
40
30
20
10
/*11. Write a Java program that prints all real solutions to the quadratic
equation ax2 +bx+c = 0. Read in a, b, c and use the quadratic formula. If the
discriminant b2 -4ac is negative, display a message stating that there are no
real solutions. */
import java.io.*;
class Quadratic
{
public static void main(String args[])throws IOException
{
double x1,x2,disc,a,b,c,imaginarypart,realpart;
InputStreamReader obj=new InputStreamReader(System.in);
BufferedReader br=new BufferedReader(obj);
System.out.println("enter a,b,c values");
a=Double.parseDouble(br.readLine());
b=Double.parseDouble(br.readLine());
c=Double.parseDouble(br.readLine());
disc=(b*b)-(4*a*c);
if(disc==0)
{
System.out.println("roots are real and equal ");

x1=x2=-b/(2*a);
System.out.println("roots are "+x1+","+x2);
}
else if(disc>0)
{
System.out.println("roots are real and unequal");
x1=(-b+Math.sqrt(disc))/(2*a);
x2=(-b-Math.sqrt(disc))/(2*a);
System.out.println("roots are "+x1+","+x2);
}
else
{
realpart=-b/(2*a);
imaginarypart=Math.sqrt(-disc)/(2*a);
System.out.println("roots are imaginary");
System.out.println(realpart+"+"+imaginarypart+"i");
System.out.println(realpart+"-"+imaginarypart+"i");
}
}
}
Output:
Run 1:
F:\java programs>javac Quadratic.java
F:\java programs>java Quadratic
enter a,b,c values
1
2

3
roots are imaginary
-1.0+1.4142135623730951i
-1.0-1.4142135623730951i
Run 2:
F:\java programs>java Quadratic
enter a,b,c values
1
4
4
roots are real and equal
roots are -2.0,-2.0
Run 3:
F:\java programs>java Quadratic
enter a,b,c values
1
5
6
roots are real and unequal
roots are -2.0,-3.0
//12. Write a program to multiply two matrices.
import java.util.*;
class Matrix {
int r1,c1,r2,c2;
Matrix(int r1,int c1,int r2,int c2) {
this.r1=r1;
this.c1=c1;
this.r2=r2;

this.c2=c2;
}
int[ ][ ] getArray(int r,int c) {
int arr[][]=new int[r][c];
System.out.println("Enter the elements for "+r+"X"+c+" Matrix:");
Scanner input=new Scanner(System.in);
for(int i=0;i<r;i++)
for(int j=0;j<c;j++)
arr[i][j]=input.nextInt();
return arr;
}
int[ ][ ] findMul(int a[ ][ ],int b[ ][ ]) {
int c[][]=new int[r1][c2];
for (int i=0;i<r1;i++)
for (int j=0;j<c2;j++) {
c[i][j]=0;
for (int k=0;k<r2;k++)
c[i][j]=c[i][j]+a[i][k]*b[k][j];
}
return c;
}
void putArray(int res[ ][ ]) {
System.out.println ("The resultant "+r1+"X"+c2+" Matrix is:");
for (int i=0;i<r1;i++) {
for (int j=0;j<c2;j++)
System.out.print(res[i][j]+" ");
System.out.println();
}
}

} //end of Test class
class MatrixMul {
public static void main(String args[ ])
{
Matrix obj1=new Matrix(2,3,3,2);
Matrix obj2=new Matrix(2,3,3,2);
int x[ ][ ],y[ ][ ],z[ ][ ];
System.out.println("MATRIX-1:");
x=obj1.getArray(2,3); //to get the matrix from user
System.out.println("MATRIX-2:");
y=obj2.getArray(3,2);
z=obj1.findMul(x,y); //to perform the multiplication
obj1.putArray(z); // to display the resultant matrix
}
}
Output:
F:\java programs>javac MatrixMul.java
F:\java programs>java MatrixMul
MATRIX-1:
Enter the elements for 2X3 Matrix:
1 2 3
4 5 6
MATRIX-2:
Enter the elements for 3X2 Matrix:
7 8
9 1
2 3
The resultant 2X2 Matrix is:

31 19
85 55
/*13. Java Program to check whether a three digit number is Armstrong or
not*/
import java.util.Scanner;
class Armstrong
{
public static void main(String args[])
{
int number=0,sum=0,rem=0,cube=0,temp=0;
Scanner scan=new Scanner(System.in);
System.out.println("Enter a number: ");
number=scan.nextInt();
System.out.println("The number entered is:"+" " +number );
temp=number;
while(number!=0)
{
rem=number%10;
cube=(int)Math.pow(rem,3);
sum=sum+cube;
number=number/10;
}
if(sum==temp)

System.out.println(temp+" "+"is an Armstrong number");
else
System.out.println(temp+" "+"is not Armstrong number");
}
}
Output:
F:\java programs>javac Armstrong.java
F:\java programs>java Armstrong
Enter a number:
153
The number entered is: 153
153 is an Armstrong number
F:\java programs>java Armstrong
Enter a number:
123
The number entered is: 123
123 is not Armstrong number
/*14. Java Program computing the area of room illustrating the concept of
single Inheritance*/
class Room
{
int length;
int breadth;
Room(int x, int y)
{
length=x;

breadth=y;
}
void area()
{
int z=length*breadth;
System.out.println("The area is: "+z);
}
}
class BedRoom extends Room
{
int height;
BedRoom(int a, int b, int c)
{
super(a,b); //passes the value to superclass
height=c;
}
void volume()
{
int v=length*breadth*height;
System.out.println("The volume is : "+v);
}
}
class SingleInheritance
{
public static void main(String args[])
{
BedRoom br=new BedRoom(10,20,30);
br.area();
br.volume();

}
}
Output:
F:\java programs>javac SingleInheritance.java
F:\java programs>java SingleInheritance
The area is: 200
The volume is : 6000
/*15. Write a program to calculate bonuses for different departments using
method overloading. */
import java.io.*;
import java.lang.*;
abstract class DepartmentBonus
{
long amount;
DepartmentBonus(long n)
{
amount=n;
}
abstract void bonus(long n);
}
class Sales extends DepartmentBonus
{
Sales(long n)
{
super(n);
}
void bonus(long n)

{
amount+=n;
System.out.println("Sales department amount is "+amount);
}
}
class Product extends DepartmentBonus
{
Product(long n)
{
super(n);
}
void bonus(long n)
{
amount+=n;
System.out.println("Production department amount is "+amount);
}
}
class Account extends DepartmentBonus
{
Account(long n)
{
super(n);
}
void bonus(long n)
{
amount+=n;
System.out.println("Accounts department amount is "+amount);
}
}

class Overload
{
public static void main(String args[])
{
DepartmentBonus d;
d=new Sales(5000);
d.bonus(600);
d=new Product(6000);
d.bonus(500);
d=new Account(1000);
d.bonus(300);
}
}
Output:
F:\java programs>javac Overload.java
F:\java programs>java Overload
Sales department amount is 5600
Production department amount is 6500
Accounts department amount is 1300
//16. Write an applet program to scroll a text.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=Param width=800 height=500>
</applet>*/
public class Param extends Applet implements Runnable
{

final Font f=new Font("Bookman Old Style",Font.BOLD,50);
String msg="Object Oriented Programming with Java Programming ";
Thread t=null;
int state;
boolean stopflag;
public void init()
{
setForeground(Color.red);
setBackground(Color.cyan);
}
public void start()
{
t=new Thread(this);
stopflag=false;
t.start();
}
public void run()
{
char ch;
for(;;)
try
{
repaint();
Thread.sleep(500);
ch=msg.charAt(0);
msg=msg.substring(1,msg.length());
msg+=ch;
if(stopflag)
break;

}
catch(InterruptedException e)
{
}
}
public void stop()
{
stopflag=true;
t=null;
}
public void paint(Graphics g)
{
g.setFont(f);
g.drawString(msg,0,100);
}
}
Output:
F:\java programs>javac Param.java
F:\java programs>appletviewer Param.java

// 17. Write an applet program to create student report
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=StudentReport width=800 height=800></applet>*/
public class StudentReport extends Applet implements ActionListener
{
int total,average;
TextField t1,t2,t3,t4,t5,t6,t7;
Thread t;
public void start()

{
try
{
t.start();
}
catch(Exception e)
{
}
}
public void init()
{
setLayout(new FlowLayout(FlowLayout.LEFT,200,20));
Button b1,b2;
Label l1=new Label("Enter the name: ");
Label l2=new Label("Enter the roll number: ");
Label l3=new Label("Physics: ");
Label l4=new Label("Chemistry: ");
Label l5=new Label("Mathematics: ");
Label l6=new Label("Computer Science: ");
t1=new TextField(20);
t2=new TextField(20);
t3=new TextField(20);
t4=new TextField(20);
t5=new TextField(20);
t6=new TextField(20);
add(l1);
add(t1);
add(l2);
add(t2);

add(l3);
add(t3);
add(l4);
add(t4);
add(l5);
add(t5);
add(l6);
add(t6);
b1=new Button("CALCULATE");
b2=new Button("CLEAR");
b1.addActionListener(this);
add(b1);
b2.addActionListener(this);
add(b2);
}
public void actionPerformed(ActionEvent ae)
{
String c=ae.getActionCommand();
if(c=="CALCULATE")
{
int m1=Integer.parseInt(t3.getText());
int m2=Integer.parseInt(t4.getText());
int m3=Integer.parseInt(t5.getText());
int m4=Integer.parseInt(t6.getText());
total=m1+m2+m3+m4;
average=total/4;
}
if(c=="CLEAR")
{

t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");
t5.setText("");
t6.setText("");
}
repaint();
}
public void paint(Graphics g)
{
g.drawString("Name: "+t1.getText(),200,480);
g.drawString("Roll number: "+t2.getText(),200,500);
g.drawString("Physics: "+t3.getText(),200,520);
g.drawString("Chemistry: "+t4.getText(),200,540);
g.drawString("Mathematics: "+t5.getText(),200,580);
g.drawString("Computer Science: "+t6.getText(),200,600);
g.drawString("Total: "+total,200,620);
g.drawString("Percentage: "+average,200,640);
}
}
Output:
F:\java programs>javac StudentReport.java
F:\java programs>appletviewer StudentReport.java

//18. Write a program to demonstrate keyboard events.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code=KeyboardDemo width=500 height=300>
</applet>*/
public class KeyboardDemo extends Applet implements KeyListener

{
String msg="";
public void init()
{
addKeyListener(this);
requestFocus();
}
public void keyPressed(KeyEvent ke)
{
showStatus("Key Down");
}
public void keyReleased(KeyEvent ke)
{
showStatus("Key Up");
}
public void keyTyped(KeyEvent ke)
{
msg+=ke.getKeyChar();
repaint();
}
public void paint(Graphics g)
{
g.drawString(msg,10,100);
}
}
Output:
F:\java programs>javac KeyboardDemo.java
F:\java programs>appletviewer KeyboardDemo.java

//19. Write an applet program to find the factorial of a number.
import java.awt.*;
import java.awt.event.*;
/*<applet code="Fact.class" height=300 width=300></applet>*/
import java.applet.Applet;
public class Fact extends Applet implements ActionListener
{
Label l1,l2; TextField
t1,t2; Button b1;
public void init(){
l1=new Label("enter the value");
add(l1);
t1=new TextField(10);
add(t1);
b1=new Button("Factorial");
add(b1); b1.addActionListener(this);
l2=new Label("Factorial of given no is");
add(l2);

t2=new TextField(10);
add(t2);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==b1)
{
int fact=fact(Integer.parseInt(t1.getText()));
t2.setText(String.valueOf(fact));
}
}
int fact(int f)
{
int s=0;
if(f==0) return
1; else
return f*fact(f-1);
}
}
F:\java programs>javac Fact.java
F:\java programs>appletviewer Fact.java

//20. Write an applet program to illustrate exception handling.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*<applet code="Div"width=230 height=250></applet>*/
public class Div extends Applet implements ActionListener
{
String msg;
TextField num1,num2,res;Label l1,l2,l3;
Button div;
public void init()
{
l1=new Label("Number 1");
l2=new Label("Number 2");
l3=new Label("result");
num1=new TextField(10);
num2=new TextField(10);
res=new TextField(10);
div=new Button("DIV");
div.addActionListener(this);

add(l1);
add(num1);
add(l2);
add(num2);
add(l3);
add(res);
add(div);
}
public void actionPerformed(ActionEvent ae)
{
String arg=ae.getActionCommand();
if(arg.equals("DIV"))
{
String s1=num1.getText();
String s2=num2.getText();
int num1=Integer.parseInt(s1);
int num2=Integer.parseInt(s2);
if(num2==0)
{
try
{
System.out.println(" ");
}
catch(Exception e)
{
System.out.println("ArithematicException"+e);
}
msg="Arithmetic";
repaint();

}
else if((num1<0)||(num2<0))
{
try
{
System.out.println("");
}
catch(Exception e)
{
System.out.println("NumberFormat"+e);
}
msg="NumberFormat";
repaint();
}
else
{
int num3=num1/num2;
res.setText(String.valueOf(num3));
}
}
}
public void paint(Graphics g)
{
g.drawString(msg,30,70);
}
}
Output:
F:\java programs>javac Div.java

F:\java programs>appletviewer Div.java
//21. Write a java program for handling mouse events
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/* <applet code="mouseevent" width=200 height=200> </applet>
*/
public class mouseevent extends Applet implements
MouseListener,MouseMotionListener
{
String msg="";
int mousex=0,mousey=0;
public void init()
{
addMouseListener(this);
addMouseMotionListener(this);
}
public void mouseClicked(MouseEvent me)
{
mousex=0;
mousey=10; msg="mouse clicked"; repaint();

}
public void mouseEntered(MouseEvent me)
{
mousex=0;
mousey=10; msg="mouse Entered"; repaint();
}
public void mouseExited(MouseEvent me)
{
mousex=0; mousey=10; msg="mouse exited";
repaint();
}
public void mousePressed(MouseEvent me)
{
mousex=me.getX(); mousey=me.getY();
msg="down";
repaint();
}
public void mouseReleased(MouseEvent me)
{
mousex=me.getX();
mousey=me.getY();
msg="Up";
repaint();
}
public void mouseDragged(MouseEvent me)
{
mousex=me.getX();
mousey=me.getY();
msg="";

showStatus("Dragged mouse at "+mousex+""+mousey);
repaint();
}
public void mouseMoved(MouseEvent me)
{
showStatus("Moving mouse at "+me.getX()+""+me.getY());
}
public void paint(Graphics g)
{
g.drawString(msg,mousex,mousey);
}
}
Output:
F:\java programs>javac mouseevent.java
F:\java programs>appletviewer mouseevent.java
/*22. Suppose that a table named Table.txt is stored in a text file. The first
line in the file is the header, and the remaining lines correspond to rows in
the table. The elements are separated by commas. Write a java program to
display the table using Labels in Grid Layout. */
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.*;
import java.awt.*;
public class ReadFile extends JFrame
{
ArrayList<JLabel> labels = new ArrayList<JLabel>();
int i=0,j=0;
Container c;
GridLayout grid;
ReadFile()
{
try
{
String csvFile = "Data.txt";
//create BufferedReader to read csv file
BufferedReader br = new BufferedReader(new FileReader(csvFile));
String line = "";
StringTokenizer st = null;
int lineNumber = 0;
int tokenNumber = 0;
//read comma separated file line by line
while ((line = br.readLine()) != null)
{
lineNumber++;
st = new StringTokenizer(line, ","); //use comma as token separator
j=0;
while (st.hasMoreTokens())

{
tokenNumber++;
labels.add(new JLabel(st.nextToken()));
j++;//column Counter
}
i++;//row counter
tokenNumber = 0;//reset token number
}
}
catch (Exception e)
{
System.err.println("CSV file cannot be read : " + e);
}
c = getContentPane ();
grid = new GridLayout (i,j);
c.setLayout (grid);
for(int i=0; i<labels.size(); i++)
{
c.add(labels.get(i));
}
setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
}
public static void main(String[] args)
{
ReadFile rf=new ReadFile();
rf.setTitle ("Grid Layout...");
rf.setSize (600,250);
rf.setVisible (true);
}

}
Output:
F:\java programs>javac ReadFile.java
F:\java programs>java ReadFile
//23. Write an applet program to design a simple calculator.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//<applet code=Calculator height=300 width=200></applet>
public class Calculator extends JApplet
{
public void init()
{
CalculatorPanel calc=new CalculatorPanel();
getContentPane().add(calc);
}
}
class CalculatorPanel extends JPanel implements ActionListener
{
JButton n1,n2,n3,n4,n5,n6,n7,n8,n9,n0,plus,minus,mul,div,dot,equal;
static JTextField result=new JTextField("0",45);

static String lastCommand=null;
JOptionPane p=new JOptionPane();
double preRes=0,secVal=0,res;
private static void assign(String no)
{
if((result.getText()).equals("0"))
result.setText(no);
else if(lastCommand=="=")
{
result.setText(no);
lastCommand=null;
}
else
result.setText(result.getText()+no);
}
public CalculatorPanel()
{
setLayout(new BorderLayout());
result.setEditable(false);
result.setSize(300,200);
add(result,BorderLayout.NORTH);
JPanel panel=new JPanel();
panel.setLayout(new GridLayout(4,4));
n7=new JButton("7");
panel.add(n7);
n7.addActionListener(this);
n8=new JButton("8");
panel.add(n8);
n8.addActionListener(this);

n9=new JButton("9");
panel.add(n9);
n9.addActionListener(this);
div=new JButton("/");
panel.add(div);
div.addActionListener(this);
n4=new JButton("4");
panel.add(n4);
n4.addActionListener(this);
n5=new JButton("5");
panel.add(n5);
n5.addActionListener(this);
n6=new JButton("6");
panel.add(n6);
n6.addActionListener(this);
mul=new JButton("*");
panel.add(mul);
mul.addActionListener(this);
n1=new JButton("1");
panel.add(n1);
n1.addActionListener(this);
n2=new JButton("2");
panel.add(n2);
n2.addActionListener(this);
n3=new JButton("3");
panel.add(n3);
n3.addActionListener(this);
minus=new JButton("-");
panel.add(minus);

minus.addActionListener(this);
dot=new JButton(".");
panel.add(dot);
dot.addActionListener(this);
n0=new JButton("0");
panel.add(n0);
n0.addActionListener(this);
equal=new JButton("=");
panel.add(equal);
equal.addActionListener(this);
plus=new JButton("+");
panel.add(plus);
plus.addActionListener(this);
add(panel,BorderLayout.CENTER);
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==n1) assign("1");
else if(ae.getSource()==n2) assign("2");
else if(ae.getSource()==n3) assign("3");
else if(ae.getSource()==n4) assign("4");
else if(ae.getSource()==n5) assign("5");
else if(ae.getSource()==n6) assign("6");
else if(ae.getSource()==n7) assign("7");
else if(ae.getSource()==n8) assign("8");
else if(ae.getSource()==n9) assign("9");
else if(ae.getSource()==n0) assign("0");
else if(ae.getSource()==dot)
{

if(((result.getText()).indexOf("."))==-1)
result.setText(result.getText()+".");
}
else if(ae.getSource()==minus)
{
preRes=Double.parseDouble(result.getText());
lastCommand="-";
result.setText("0");
}
else if(ae.getSource()==div)
{
preRes=Double.parseDouble(result.getText());
lastCommand="/";
result.setText("0");
}
else if(ae.getSource()==equal)
{
secVal=Double.parseDouble(result.getText());
if(lastCommand.equals("/"))
res=preRes/secVal;
else if(lastCommand.equals("*"))
res=preRes*secVal;
else if(lastCommand.equals("-"))
res=preRes-secVal;
else if(lastCommand.equals("+"))
res=preRes+secVal;
result.setText(" "+res);
lastCommand="=";
}

else if(ae.getSource()==mul)
{
preRes=Double.parseDouble(result.getText());
lastCommand="*";
result.setText("0");
}
else if(ae.getSource()==plus)
{
preRes=Double.parseDouble(result.getText());
lastCommand="+";
result.setText("0");
}
}
}
Output:
F:\java programs>javac Calculator.java
F:\java programs>appletviewer Calculator.java

/*24. Write a java program to simulate a traffic light. The program lets the
user select one of the three lights: red, yellow or green. On selecting a
button, an appropriate message with ”Stop” or “Ready” or “ Go” should
appear above the button's selected color. */
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*<applet code="TrafficSignal.class" height=500 width=300></applet>*/
public class TrafficSignal extends Applet implements Runnable
{
Thread t;
Font f, f1;
int i = 0, a = 0, j = 0;
public void init()
{
setBackground(Color.lightGray);
f = new Font("TimesNewRoman", f.ITALIC, 28);
f1 = new Font("TimesNewRoman", Font.ITALIC + Font.BOLD, 28);
}
public void start()
{
t = new Thread(this);
t.start();
}
public void run()
{
for (i = 10; i >= 0; i--)//countdown
{
try

{
Thread.sleep(1000);
}
catch (Exception e)
{
System.out.println(e);
}
if (i <= 10 && i > 3)//red
{
a = 1;
repaint();
}
else if (i <= 3 && i > 0)//yellow
{
a = 2;
repaint();
}
else if (i == 0)//green
{
for (j = 0; j < 10; j++)
{
a = 3;
try
{
Thread.sleep(1000);
}
catch (Exception e)
{
System.out.println(e);

}
repaint();
}
if (j == 10)//end of green(return to red)
{
run();
}
}
}
repaint();
}
public void paint(Graphics g)
{
setBackground(Color.lightGray);//ROAD
g.setColor(Color.black);//POLE UP
g.fillRect(150, 150, 50, 150);
g.drawRect(150, 150, 50, 150);
g.setColor(Color.black);//POLE DOWN
g.fillRect(165, 300, 20, 155);
g.drawRect(165, 300, 20, 155);
g.drawOval(150, 150, 50, 50);//RED
g.drawOval(150, 200, 50, 50);//YELLOW
g.drawOval(150, 250, 50, 50);//GREEN
g.setColor(Color.red);//COUNTDOWN STOP
g.setFont(f);
g.drawString("" + i, 50, 50);
if (a == 1)//RED SIGNAL
{
g.setColor(Color.red);

g.fillOval(150, 150, 50, 50);
g.drawOval(150, 150, 50, 50);
g.drawString("STOP", 50, 150);
}
if (a == 2)//YELLOW SIGNAL
{
g.setColor(Color.yellow);
g.fillOval(150, 200, 50, 50);
g.drawOval(150, 200, 50, 50);
g.drawString("READY", 50, 200);
}
if (a == 3)//GREENSIGNAL
{
g.setColor(Color.blue);//countdown
g.setFont(f);
g.drawString("" + j, 150, 50);
g.setColor(Color.green);
g.fillOval(150, 250, 50, 50);
g.drawOval(150, 250, 50, 50);
g.drawString("GO", 50, 250);
}
int x1[] = {220, 300, 300, 280};
int y1[] = {250, 150, 250, 150};
int n1 = 4;
int n2 = 3;
int x2[] = {340, 380, 380};
int y2[] = {150, 100, 150};
int x3[] = {460, 460, 500};
int y3[] = {150, 100, 150};

}
}
Output:
F:\java programs>javac TrafficSignal.java
F:\java programs>appletviewer TrafficSignal.java