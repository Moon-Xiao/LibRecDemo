<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<%--
Created by IntelliJ IDEA.
User: Moon
Date: 2017/6/2
Time: 22:11
To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel='icon' href='${pageContext.request.contextPath}/pic/icon.ico ' type='image/x-ico'/>
    <title>Recommend</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet">
</head>
<body>
<div id="header">
    <img class="icon" src="${pageContext.request.contextPath}/pic/icon.jpg"/>
    <span class="title">
            Moon's Comment Project
    </span>
</div>
<div id="comment" style="margin-top: 6px">
    <div class="row" style="margin: 0">
        <div class="col-xs-4 col-md-3 left">
            <div style="padding-right: 5px">

                <div class="panel panel-info" style="min-height: 200px;min-width: 200px">
                    <div class="panel-heading text-center" style="height: 55px">
                        <span style="font-size: 22px">Algorithm</span>
                    </div>
                    <div class="panel-body">
                        <div class="item">

                            <div class="leave">
                                <div class="leave-root">
                                    <span class="glyphicon glyphicon-check"></span><span>MemoryBased-CF</span>
                                </div>
                                <div class="leave-items" id="MEMORY">
                                    <div class="leave-item" id="MEMORY-UCF" onclick="algorithmOnclick('MEMORY-UCF')">
                                        <span>UserBased-CF</span>
                                    </div>
                                    <div class="leave-item" id="MEMORY-ICF" onclick="algorithmOnclick('MEMORY-ICF')">
                                        <span>ItemBased-CF</span>
                                    </div>
                                </div>
                            </div>

                            <div class="leave">
                                <div class="leave-root">
                                    <span class="glyphicon glyphicon-check"></span><span>ModelBased-CF</span>
                                </div>
                                <div class="leave-items" id="MODEL">
                                    <div class="leave-item" id="MODEL-MF" onclick="algorithmOnclick('MODEL-MF')">
                                        <span>Matrix Factorization</span>
                                    </div>
                                    <div class="leave-item" id="MODEL-PG" onclick="algorithmOnclick('MODEL-PG')">
                                        <span>Probabilistic Graphical</span>
                                    </div>
                                    <div class="leave-item" id="MODEL-S" onclick="algorithmOnclick('MODEL-S')">
                                        <span>Social</span>
                                    </div>
                                </div>

                            </div>

                            <div class="leave">
                                <div class="leave-root">
                                    <span class="glyphicon glyphicon-check"></span><span>Others</span>
                                </div>
                                <div class="leave-items">
                                    <div class="leave-item">
                                        <span>......</span>
                                    </div>
                                    <div class="leave-item">
                                        <span>......</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="panel panel-success">
                    <div class="panel-heading text-center" style="height: 55px">
                        <span style="font-size: 22px">Introduction</span>
                    </div>
                    <div class="panel-body">
                        <p style="margin-left: 8px;font-size: 16px;color: #468847">
                            choose algorithm(Al);<br>
                            set splitter configure;<br>
                            [ MemoryBased-CF]choose similarity;<br>
                            and set recommend configure;<br>
                            press "Recommend" button;<br><br>
                            then...<br>
                            recommend result will show,<br>
                            [ModelBased-CF]"Loss function change curve" will display,<br>
                            and the evaluation will also show.<br>
                        </p>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-xs-8 col-md-9 right">
            <div class="right-body" style="min-height: 700px">
                <div class="row">
                    <div class="col-md-5" style="padding-right: 0">
                        <div id="evaluate-panel" class="panel panel-info">
                            <div class="panel-heading right-sub-heading">
                                <span class="navbar-left">Evaluation</span>
                            </div>
                            <div id="evaluate-body" class="panel-body">
                                <%--js注入--%>
                            </div>
                        </div>
                        <div class="panel panel-info">
                            <div class="panel-heading right-sub-heading">
                                <!-- split button -->
                                <span class="navbar-left">Splitter</span>
                                <div class="btn-group navbar-right">
                                    <button type="button" class="btn btn-default dropdown-toggle btn-down"
                                            data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="false"
                                            style="width: 125px;margin-right: 10%">
                                        <span id="split"></span><span class="caret"></span>
                                    </button>
                                    <ul id="split-list" class="dropdown-menu">
                                        <c:forEach items="${splitList}" var="split">
                                            <li onclick="splitFresh('${split}')">
                                                <a>${split}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div id="split-body" class="panel-body">
                                <%--js注入--%>
                            </div>
                        </div>
                        <c:if test="${not fn:startsWith(param.type,'MODEL')}">
                            <div class="panel panel-info">
                                <div class="panel-heading right-sub-heading">
                                    <!-- Similarity button -->
                                    <span class="navbar-left">Similarity</span>
                                    <div class="btn-group navbar-right">
                                        <button type="button" class="btn btn-default dropdown-toggle btn-down"
                                                data-toggle="dropdown"
                                                style="width: 200px"
                                                aria-haspopup="true" aria-expanded="false">
                                            <span id="similarity"></span><span class="caret"></span>
                                        </button>
                                        <ul id="similarity-list" class="dropdown-menu">
                                            <c:forEach items="${similarityList}" var="similarity">
                                                <li onclick="similarityFresh('${similarity}')">
                                                    <a>${similarity}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div id="similarity-body" class="panel-body">
                                        <%--js注入--%>
                                </div>
                            </div>
                        </c:if>
                        <div class="panel panel-info">
                            <div class="panel-heading right-sub-heading">
                                <!-- RecommendSocket button -->
                                <span class="navbar-left">Recommend</span>
                                <div class="btn-group navbar-right">
                                    <button type="button" class="btn btn-default dropdown-toggle btn-down"
                                            data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="false" style="width: 200px">
                                        <span id="recommend"></span><span class="caret"></span>
                                    </button>
                                    <ul id="recommend-list" class="dropdown-menu">
                                        <c:forEach items="${recommendList}" var="recommend">
                                            <li onclick="recommendFresh('${recommend}')">
                                                <a>${recommend}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="panel-body" id="recommend-body">
                                <%--js注入--%>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div id="chart_container">
                            <div class="panel panel-info">
                                <div class="panel-body">
                                    <div id="chart_model"
                                         style="height: 400px;padding: 10px;padding-bottom: 5px;margin: -15px">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-info">
                            <div class="panel-heading right-sub-heading">
                                <span class="navbar-left">Result</span>
                            </div>
                            <div class="panel-body">
                                <form id="search-form" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="userFilter" class="col-sm-2 control-label">UserFilter</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="userFilter"
                                                   placeholder="UserFilter">
                                        </div>
                                        <label for="itemFilter" class="col-sm-2 control-label">ItemFilter</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="itemFilter"
                                                   placeholder="ItemFilter">
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" content="form-control" onclick="search()"
                                                   class="btn btn-default" value="Search"/>
                                        </div>

                                    </div>
                                </form>
                                <div id="result">
                                    <%--js注入--%>
                                </div>
                                <div class="text-center">
                                    <nav aria-label="Page navigation" style="margin-top:10px;height: 55px;">
                                        <ul class="pagination">
                                            <li class="disabled" id="before" onclick="getBefore()">
                                                <a aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a>
                                                    <span id="pageNum">1</span>
                                                </a>
                                            </li>
                                            <li class="disabled" id="after" onclick="getAfter()">
                                                <a aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                            <span style="display: inline-block;line-height: 35px;height: 35px;margin-left: 10px">PageSize：<span
                                                    id="pageSize">0</span></span>
                                        </ul>

                                    </nav>
                                </div>


                            </div>

                            <div class="panel-footer text-right" style="background-color:  rgba(195, 239, 248, 0.36)">
                                <button onclick="start()" class="btn"
                                        style="background-color: white;width: 106px;height: 34px;margin-right: 20px">
                                    <p style="color: #3a87ad"><b>Recommend</b></p>
                                </button>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <p>
        Algorithm support by :
        <a href="https://www.librec.net/">
            <img src="${pageContext.request.contextPath}/pic/Librec.PNG"/></a>
    </p>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.js"></script>
<script>
    var webSocket =
        new WebSocket('ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/recommend');

    var myChart = echarts.init(document.getElementById("chart_model"));
    var modelData = [];
    var option = {
        title: {
            text: 'Loss function change curve'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                return 'iterations:' + params.value[0] + '<br/>' + 'loss:' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            name: 'Iteration Num',
            type: '',
            nameTextStyle: {
                color: 'red'
            }
        },
        yAxis: {
            name: 'Loss function',
            type: 'value',
            nameTextStyle: {
                color: 'red'
            }
        },
        series: [{
            name: 'loss',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            markLine: {
                data: [
                    {
                        type: 'max',
                        name: 'Max',
                        label: {
                            normal: {
                                position: 'end',
                                formatter: 'Max:{c}'
                            }
                        }
                    },
                    {
                        type: 'min',
                        name: 'Min',
                        label: {
                            normal: {
                                position: 'end',
                                formatter: 'Min:{c}'
                            }
                        }
                    }
                ]

            },
            data: modelData
        }]
    };

    $(".leave-root").bind("click", function (event) {
        $(this).parent().children(".leave-items").slideToggle();
    });

    /*loading*/
    $(function () {
        var select_type = '${param.type}';
        $('#' + select_type).addClass("type-active");
        $('#' + select_type.split("-")[0]).slideDown();
        $("#chart_container").hide();
        $("#evaluate-panel").hide();
        splitFresh($("#split-list").find("a").first().html());
        similarityFresh($("#similarity-list").find("a").first().html());
        recommendFresh($("#recommend-list").find("a").first().html());
    });

    function algorithmOnclick(type) {
        window.location.href = "${pageContext.request.contextPath}/index?type=" + type;
    }

    /*refresh*/
    function createComment(data) {
        var content = '<form class="form-horizontal">';
        for (var key in data) {
            content += '<div class="form-group">';
            content += '<label title="' + key + '" class="col-sm-6">' + key + '</label>' +
                '<div class="col-sm-6">';
            if (typeof(data[key]) === "object") {
                content += '<select name="' + key + '" class="form-control">';
                for (item in data[key]) {
                    content += '<option value="' + data[key][item] + '">' + data[key][item] + '</option>';
                }
                content += '</select>';
            } else {
                content += '<input name="' + key + '" type="text" class="form-control" value="' + data[key] + '">';
            }
            content += '</div></div>';
        }
        content += " </form>";
        return content;
    }
    /*split*/
    function splitFresh(type) {
        $("#split").html(type);
        $.post("getSplitConf.ajax", {split: type}, function (data) {
            $("#split-body").html(createComment(data))
        }, "json");
    }
    /*similarity*/
    function similarityFresh(type) {
        $("#similarity").html(type);
        $.post("getSimilarityConf.ajax", {similarity: type, recommend: '${param.type}'}, function (data) {
            $("#similarity-body").html(createComment(data))
        }, "json");
    }
    /*RecommendSocket*/
    function recommendFresh(type) {
        $("#recommend").html(type);
        $.post("getRecommendConf.ajax", {recommend: '${param.type}'}, function (data) {
            $("#recommend-body").html(createComment(data))
        }, "json");
    }
    /*package data*/
    function packData() {
        var data = {};
        data["data.model.splitter"] = $("#split").html();
        $(".panel-body input,select").each(function () {
            data[$(this).attr("name")] = $(this).val();
        });
        return data;
    }
    function changeToTable(data) {
        var table = '<table class="table table-hover">' +
            '<thead><tr><th class="col-xs-4">user</th><th class="col-xs-4">item</th><th class="col-xs-4">value</th></tr></thead><tbody>';
        data.forEach(function (e) {
            table += '<tr><th>' + e[0] + '</th><th>' + e[1] + '</th><th>' + e[2] + '</th></tr>';
        });
        table += '</tbody></table>';
        return table;
    }
    var pageNum = 1;
    var pageSize = 0;
    function start() {
        console.log("recommend");
        if ('${fn:split(param.type,"-")[0]}' == 'MODEL') {
            $("#chart_container").slideDown();
            modelData = [];
            myChart.setOption(option);
        }
        $("#result").html('<img src="${pageContext.request.contextPath}/pic/loading.gif"/>');
        webSocket.send(
            JSON.stringify(
                {
                    type: "recommend",
                    data: {
                        conf: packData(),
                        similarity: $("#similarity").html(),
                        recommend: {
                            name: $("#recommend").html(),
                            type: '${fn:split(param.type,"-")[0]}'
                        }
                    }
                }
            )
        )
    }
    function search() {
        console.log("search");
        var userFilter = $("#userFilter").val();
        var itemFilter = $("#itemFilter").val();
        var userList = [];
        var itemList = [];
        if (userFilter !== "")
            userList = userFilter.split(',');
        if (itemFilter !== "")
            itemList = itemFilter.split(',');
        webSocket.send(
            JSON.stringify(
                {
                    type: "filter",
                    data: {
                        user_list: userList,
                        item_list: itemList
                    }
                }
            )
        );
    }
    function getBefore() {
        if (pageNum <= 1) {
            return false;
        }
        console.log("get_info");
        pageNum--;
        $("#pageNum").html(pageNum);
        if (pageNum == 1) {
            $("#before").addClass("disabled");
        }
        if (pageNum != pageSize) {
            $("#after").removeClass("disabled");
        }
        webSocket.send(
            JSON.stringify(
                {
                    type: "get_info",
                    data: {
                        pageNum: pageNum
                    }
                }
            )
        );
        return true;
    }
    function getAfter() {
        if (pageNum >= pageSize) {
            return false;
        }
        console.log("get_info");
        pageNum++;
        $("#pageNum").html(pageNum);
        if (pageNum != 1) {
            $("#before").removeClass("disabled");
        }
        if (pageNum == pageSize) {
            $("#after").addClass("disabled");
        }
        webSocket.send(
            JSON.stringify(
                {
                    type: "get_info",
                    data: {
                        pageNum: pageNum
                    }
                }
            )
        );
        return true;
    }
    function stop() {
        console.log("stop");
        webSocket.send(
            JSON.stringify(
                {
                    type: "stop",
                    data: {}
                }
            )
        );
    }

    webSocket.onerror = function (event) {
        console.log(event.data);
    };

    webSocket.onopen = function (event) {
        console.log("Connection established");
    };

    webSocket.onmessage = function (event) {
        console.log(event.data);
        var result = JSON.parse(event.data);
        /*type+data*/
        var type = result.type;
        var data = result.data;
        switch (type) {
            case "error":
                $("#result").html('<span class="text-danger">' + data + '</span>');
                break;
            case "items":
                $("#result").html(changeToTable(data));
                break;
            case "page_size":
                $("#pageSize").html(data);
                pageSize = data;
                $("#pageNum").html(1);
                pageNum = 1;
                if (pageSize != 0) {
                    $("#after").removeClass("disabled");
                } else {
                    $("#before").addClass("disabled");
                    $("#after").addClass("disabled");
                }
                break;
            case "model":
                modelData.push(data);
                myChart.setOption({
                    series: [{
                        name: 'loss',
                        data: modelData
                    }]
                });
                break;
            case "evaluate":
                $("#evaluate-panel").slideDown();
                var i = 0;
                var table = '<table class="table table-hover">' +
                    '<thead><tr><th class="col-xs-6">name</th><th class="col-xs-6">value</th></tr></thead><tbody>';
                for (key in data) {
                    i++;
                    table += '<tr><th>' + key + '</th><th>' + data[key] + '</th></tr>';
                }
                table += '</tbody></table>';
                if (i == 0)
                    $("#evaluate-body").html('<span class="text-danger">no evaluation!</span>');
                else
                    $("#evaluate-body").html(table);
                break;
            default:
        }
    };
</script>
</body>
</html>