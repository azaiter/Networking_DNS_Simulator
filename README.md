_**Client-Server Domain Server Mimicking Program**_

When a computer needs to communicate with another computer, it needs to know the other computer’s address. An Internet protocol (IP) address uniquely identifies the computer on the Internet, An IP address consists of four dotted decimal numbers between 0 and 255, such as 130.254.204.31. Since it is not easy to remember so many numbers, they are often mapped to meaningful names called domain names, such as example.edu. Special servers called Domain Name Servers (DNS) on the Internet translate host names into IP addresses. When a computer contacts example.edu, it first asks the DNS to translate this domain name into a numeric IP address and then sends the request using the IP address. In this assignment, we will write a simple client-server program that simulates DNS.

This package contains 4 Java Programs and 1 Java Interface as following:

* DNS_TCP_Client and DNS_TCP_Server: Each of those programs serve as a TCP client/server for simple DNS program.
>> Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server and the server will reply with IP of the domain. Sending zero as response will terminate the connection of the client.

* DNS_UDP_Client and DNS_UDP_Server: Each of those programs serve as a UDP client/server for simple DNS program.
>> Usage: Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server and the server will reply with IP of the domain. Sending zero as response will terminate the connection of the client. 

* DNS_Utils: An abstract class that contains server/client settings and all common helper functions among the programs.

**_Note that the programs were designed to serve multiple clients simultaneously/asynchronously._**