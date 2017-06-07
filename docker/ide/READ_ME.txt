Install maven cert:

This certificate goes into the truststore of the JRE being utlized by your Maven installation.
Import the certificate into your JRE's trust store

${JDK_HOME}/bin/keytool -import -alias iassoftware.com -file iassoftware.cer -keystore ${JRE_HOME}/lib/security/cacerts

Notes:
Every JRE contains a truststore named "cacerts"
The default password for this store is "changeit"
Every JDK has a JRE
For installation using CMD in windows, see comment below



Generate a new ssh key for bitbucket or whatever ...
If you don't have an existing SSH key that you wish to use, generate one as follows:
Open a terminal on your local computer and enter the following:

ssh-keygen -t rsa -C "your_email@example.com"
Associating the key with your email address helps you to identify the key later on.

Just press <Enter> to accept the default location and file name. If the .ssh directory doesn't exist, the system creates one for you.
Enter, and re-enter, a passphrase when prompted.

Optional - Use Docker from with in container.
If you have a need to create containers from with in another containers you should start this container with 
a volume mounted to the host's docker.sock location. See the example below

docker run -it -e DISPLAY -v /opt/dockermount/:/opt/dockermount/ -v /var/run/docker.sock:/var/run/docker.sock --net=host --name intellij-container intellij
