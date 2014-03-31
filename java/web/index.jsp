<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 23.03.2014
  Time: 7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="bot.Controller" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
        <style>
            body, html {
                position: relative;
                width: 100%;
                height: 100%;
            }
            .section {
                width: 978px;
                margin: 0 auto;
                text-align: center;
                padding-top: 40px;
            }
            .button {
                cursor: pointer;
                vertical-align: middle;
            }
            .button img {
                display: inline-block;
                width: 128;
                height: auto;
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center; font-size: 48px;"><span style="color:lightgreen">Eco</span><span style="color:crimson">BOT</span></h1>
        <div class="section">
            <div id="controllers" <%=(Controller.getInstance().isConnected() ? "" : "style='display:none'")%>>
                <%--<span id="mdisconn" class="button"><img src="css/png/internet_link_3415.png" alt="" style="width: 16px; height: auto;"/>Disconnect</span>--%>
                <center>
                <table>
                    <tr>
                        <td></td>
                        <td><span id="mforward" class="button"><img src="css/png/2uparrow_6627.png" alt=""/></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><span id="mleft" class="button"><img src="css/png/2leftarrow_9443.png" alt=""/></span></td>
                        <td><span id="mstop" class="button"><img src="css/png/stop1hot_5110.png" alt=""/></span></td>
                        <td><span id="mright" class="button"><img src="css/png/2rightarrow_5635.png" alt=""/></span></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><span id="mback" class="button"><img src="css/png/2downarrow_1330.png" alt=""/></span></td>
                        <td></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>Высота</td>
                        <td>Давление</td>
                        <td>Температура</td>
                        <td>V</td>
                        <td>CO2</td>
                        <td>CH4</td>
                        <td>Влажность</td>
                    </tr>
                    <tr id="res_td"></tr>
                </table>
                <div>
                    <pre id="log"></pre>
                </div>
                <textarea id="results" cols="100" rows="10"></textarea>
                </center>
            </div>
            <span id="mconn" class="button"><img src="css/png/internet_link_02_9161.png" alt=""/>Connect</span>
        </div>

        <script src="http://d3js.org/d3.v3.min.js"></script>
        <script>
            var logArea = d3.select('#log');
            var res = d3.select("#results");
            var res_td = d3.select("#res_td");
            d3.selectAll('.button')
                .on('click', function(d) {
                    var id = d3.select(this).attr('id');
                    d3.xhr('./act?act=' + id).get(handle(id));
                });

            function handle(id) {
                return function(err, data) {
                    if (err) {
                        logArea.text(err.toString());
                        return;
                    }

                    if (id == 'mconn') {
                        d3.select("#controllers").style('display', null);
                        d3.select("#mconn").style('display', 'none');
                        load();
                    }
                    else if(id == 'mdisconn') {
                        d3.select("#mconn").style('display', null);
                        d3.select("#controllers").style('display', 'none');
                    }
                    logArea.text(data.responseText);
                }
            }

            var lastId;
            function load() {
                d3.xhr('./data').get(parse);
            }

            function parse(err, data) {
                if(err || !data) {
                    console.log('error', err);
                }
                else {
                    res.text(data.responseText);
                    console.log(data.responseText);
                    try {
                        var ddd = JSON.parse(data.responseText.replace("$", ""));
                        var it = res_td.selectAll("td");
                        it.remove();
                        it.data(ddd)
                            .enter()
                            .append("td")
                            .text(function(d) {
                                return d;
                            });
                    }
                    catch (e){
                    }



                }
                setTimeout(load, 1500);
            }
        </script>
    </body>
</html>
