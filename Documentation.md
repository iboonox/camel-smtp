#Documentation
# Introduction #

camel-smtp is a camel component which acts as consumer by accepting messages via smtp and forward them to a camel route.


# Configuration #

> To use it just add this to your camel route:

> from("smtp:**bindIp**:**bindPort**").to("endpoint");


> Where **bindIp** is the ipaddress and **bindPort** is the port which are used to bind the server.


> For a full list of options see here:

  * **enforceHeloEhlo**
> > Enfore the need of a helo or ehlo when connection to the smtp server. Default is true

  * **enforceBrackets**
> > Enforce brackets on rcpt and mail. Default is true

  * **greeting**
> > Set the greeting which will get used and send back the client once it connect to the smtp server. Default is "Camel SMTP"

  * maxMessageSize
> > Set the max message size before rejecting the mail. Default is unlimited

  * **helloName**
> > Set the helloName to return the client when it send the EHLO or HELO command. Default is localhost

  * **localDomains**
> > Comma-seperated list of domains for which the smtp server should accept mails. All recipients which are not within this domains get rejected. Default is to accept mail to every rcpt