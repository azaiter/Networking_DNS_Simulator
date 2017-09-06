_**Client-Server Domain Server Mimicking Program**_

When a computer needs to communicate with another computer, it needs to know the other computer’s address. An Internet protocol (IP) address uniquely identifies the computer on the Internet, An IP address consists of four dotted decimal numbers between 0 and 255, such as 130.254.204.31. Since it is not easy to remember so many numbers, they are often mapped to meaningful names called domain names, such as example.edu. Special servers called Domain Name Servers (DNS) on the Internet translate host names into IP addresses. When a computer contacts example.edu, it first asks the DNS to translate this domain name into a numeric IP address and then sends the request using the IP address. This is a simple client-server program that simulates DNS.

**This package contains 4 Java Programs and 1 Java Interface as the following:**

* DNS_TCP_Client and DNS_TCP_Server: Each of those programs serve as a TCP client/server for simple DNS program.
>> Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server and the server will reply with IP of the domain and number of clients being served at the time of request. Sending zero as response will terminate the connection of the client and reduce the number of clients connected by one.

* DNS_UDP_Client and DNS_UDP_Server: Each of those programs serve as a UDP client/server for simple DNS program.
>> Usage: Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server and the server will reply with IP of the domain, it will also increment the number of requests by one. Sending zero as response will terminate the connection of the client. 

* DNS_Utils: An abstract class that contains server/client settings and all common helper functions among the programs.

**_Note that the programs were designed to serve multiple clients simultaneously/asynchronously._**

**The servers keep track of the following while the server is running:**
* TCP server keeps track of the number of concurrent connections the server is having. The initial value of it is zero and it zeros the count each time the TCP server starts.
* UDP server keeps tracks of the number of requests sent to it because of the nature of the UDP calls, it doesn't have a mechanism to effectively track user sessions. 

_Note that the count is stored in a Random Access Files, their names are specified in DNS_Utils._