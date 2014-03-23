<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 23.03.2014
  Time: 7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    <title></title>
    </head>
    <body>
        <button id="mconn">Connect</button>
        <button id="mdisconn">Disconnect</button>
        <button id="mforward">Forward</button>
        <button id="mleft">Left</button>
        <button id="mright">Right</button>
        <button id="mback">Back</button>
        <button id="mstop">Stop</button>

        <table>
            <tr>
                <td></td>
                <td><span></span></td>
                <td></td>
            </tr>
            <tr>
                <td><span></span></td>
                <td><span></span></td>
                <td><span></span></td>
            </tr>
            <tr>
                <td></td>
                <td><span></span></td>
                <td></td>
            </tr>
        </table>


        <script src="http://d3js.org/d3.v3.min.js"></script>
        <script>
            d3.selectAll('button')
                .on('click', function(d) {
                    var id = d3.select(this).attr('id');
                    d3.xhr('./act?act=' + id).get(function() {});
                })
        </script>
    </body>
</html>
