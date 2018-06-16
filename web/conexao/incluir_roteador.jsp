<%-- 
    Document   : incluir_roteador
    Created on : Jun 8, 2018, 10:45:01 AM
    Author     : natan
--%>





<%@page import="me.legrange.mikrotik.ApiConnectionException"%>
<%@page import="me.legrange.mikrotik.ResultListener"%>
<%@page import="java.util.Map"%>
<%@page import="me.legrange.mikrotik.MikrotikApiException"%>
<%@page import="java.util.List"%>
<%@page import="me.legrange.mikrotik.ApiConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%

    ApiConnection con = ApiConnection.connect("192.168.88.1"); // connect to router

    con.login("sistema", "sistema"); // log in to router
    String tag = con.execute("/interface/wireless/monitor .id=wlan1 return signal-to-noise",
            new ResultListener() {

        public void receive(Map<String, String> result) {
            System.out.println(result);
        }

        public void error(MikrotikApiException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        public void completed() {
            System.out.println("Asynchronous command has finished");
        }

    }
    );
    con.close();

%>