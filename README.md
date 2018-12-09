# ![SK IRC logo](./src/main/resources/images/logo.png) SK-IRC Client - chat app

Simple chat app created with JavaFX and Socket.

## Table of contents

-   [General info](#general-info)
-   [Technologies](#technologies)
-   [Description of the communication protocol](#description-of-the-communication-protocol)
-   [Download](#download)
-   [You may also like](#you-may-also-like)
-   [Build information](#build-information)

## General info

This project is simple IRC chat. It was created for completing the subject of Computer Networks.

## Technologies

-   JavaFx
-   Sockets

## Description of the communication protocol

### Types of messages

-   login - login to the server
-   logout - logout from the server
-   create - create a new room
-   enter - joining an existing room
-   leave - leave the room
-   rooms - request to send a list of rooms
-   users - request to send a list of users in the room
-   message - sending a message

### Examples of messages

| Message sent by the client            | Positive server response                      | Negative server response    |
| ------------------------------------- | --------------------------------------------- | --------------------------- |
| \$login:username#                     | \$login:ok#                                   | \$login:notOk#              |
| \$logout#                             | \$logout:ok#                                  | \$logout:notOk#             |
| \$create:roomName#                    | \$create:ok#                                  | \$create:notOk#             |
| \$enter:roomName#                     | \$enter:ok#                                   | \$enter:notOk#              |
| \$leave:roomName#                     | \$leave:ok#                                   | \$leave:notOk#              |
| \$rooms#                              | \$rooms:roomName;quantity room2Name;quantity# | none                        |
| \$users#                              | \$users:username username2#                   | none                        |
| \$message:useraname;message;sentDate# | none                                          | none                        |
| Przesyłana wiadomość                  | \$message:useraname;message;sentDate#         | none                        |

## Download

You can [download](https://github.com/ioioiolab2018/SK-Java/releases) the latest installable version of SK-IRC client for Windows, Linux and macOS.

## You may also like

-   [SK-IRC Server](https://github.com/ioioiolab2018/SK-C) - IRC server for this client.

## Build information

-   Master: [![Build Status](https://travis-ci.org/ioioiolab2018/SK-Java.svg?branch=master)](https://travis-ci.org/ioioiolab2018/SK-Java)
-   Release: v1.0.0 [![Build Status](https://travis-ci.org/ioioiolab2018/SK-Java.svg?branch=v1.0.0)](https://travis-ci.org/ioioiolab2018/SK-Java)
