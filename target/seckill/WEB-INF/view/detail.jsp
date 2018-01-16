<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表详情页</title>
    <%@include file="comment/head.jsp"%>
    <%@include file="comment/tag.jsp"%>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel panel-heading">
                <h1>${seckill.seckillName}</h1>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <span class="glyphicon glyphicon-time"></span>
                    <span class="glyphicon" id="seckill-box"></span>
                </h2>
            </div>
        </div>
    </div>
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhoneKey" placeholder="请输入手机号" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button class="btn btn-success" type="button" id="killPhoneBtn">
                        <span class="glyphicon glyphicon-phone"></span>
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--通过bootcdn获取以下两个插件--%>
    <script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
    <script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
    <%--交互逻辑--%>
    <script src="${path}/static/js/seckill.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            seckill.detail.init({
                seckillId : ${seckill.seckillId},
                startTime : ${seckill.beginTime.time},
                endTime : ${seckill.endTime.time}
            })
        })
    </script>
</body>
</html>

