<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <script src="<%=basepath%>Cesium-1.42/Build/Cesium/Cesium.js"></script>
    <script src="<%=basepath%>js/jquery/jquery-2.0.3.min.js"></script>
    <script src="<%=basepath%>bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basepath%>bootstrap/js/bootstrap-paginator.min.js"></script>
    <link rel="stylesheet" href="<%=basepath%>bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%=basepath%>Cesium-1.42/Build/Cesium/Widgets/widgets.css"/>
    <link rel="stylesheet" href="<%=basepath%>css/leftMenu.css"/>
</head>
<body>
<style>
    /*html, body, #cesiumContainer {
        height: 100%; margin: 0; padding: 0; overflow: hidden;
    }*/
</style>
<%--标题栏--%>
<div class="page-header" style="background-color: #3482c6 ;">
    <h1><img src="<%=basepath%>img/canlianLogo.jpg" width="40px" style="margin-right: 15px"/>无障碍设施管理</h1>
</div>


<%--左侧栏--%>
<div class="row">
    <%--<div class="col-md-2">
        <div class="panel-group table-responsive" role="tablist">
            <div class="panel panel-primary leftMenu">
                <!-- 利用data-target指定要折叠的分组列表 -->
                <div class="panel-heading" id="collapseListGroupHeading1" data-toggle="collapse" data-target="#collapseListGroup1" role="tab" style="background-color: #73b3eb">
                    <h4 class="panel-title">
                        图层列表
                        <span class="glyphicon glyphicon-chevron-up right"></span>
                    </h4>
                </div>
                <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                <div id="collapseListGroup1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <!-- 利用data-target指定URL -->
                            <input type="checkbox" name="tuceng" value="canlian" id="canlian">残联设施位置图层
                            &lt;%&ndash;<button class="menu-item-left" data-target="test2.html">
                                分组项1-1
                            </button>&ndash;%&gt;
                        </li>
                        &lt;%&ndash;<li class="list-group-item" >
                            <!-- 利用data-target指定URL -->
                            <input type="checkbox"  name="tuceng" value="tianmen" id="tianmen">天门图层
                            &lt;%&ndash;<button class="menu-item-left" data-target="test2.html">
                                分组项2-1
                            </button>&ndash;%&gt;
                        </li>
                        <li class="list-group-item" name="tuceng" >
                            <!-- 利用data-target指定URL -->
                            <input type="checkbox" name="tuceng" value="tianditu" id="tianditu">天地图标注图层
                            &lt;%&ndash;<button class="menu-item-left" data-target="test2.html">
                                分组项2-1
                            </button>&ndash;%&gt;
                        </li>&ndash;%&gt;
                    </ul>
                </div>
            </div><!--panel end-->
        </div>
    </div>--%>
    <%--Cesium内容--%>
    <div id="cesiumContainer" ></div>

    <div class="navbar navbar-inverse navbar-fixed-bottom" style="background-color: white;height:200px;OVERFLOW-Y: auto; OVERFLOW-X:hidden;display: none" id="pointMessage">
        <div class="navbar-inner">
            <div>
                <ul id="otherOption" style="float:right;margin-top: 0px" class="pagination">
                </ul>
            </div>
            <div id="pointContent">
                <label style="font-size: 18px;margin-left: 0%" >图层设施信息</label>
                <button style="float: right" onclick="cancelShowTheTable()"><span class="glyphicon glyphicon-remove"></span></button>
                <table class="table table-bordered table-hover" style="width:100%;" id="pointMessageTable"> <!-- table table-bordered 带边框的样式 -->
                </table>
            </div>
            <div style="text-align:center;">
                <ul id="pointOption" <%--style="float: left"--%>>
                </ul>
                <ul class="pagination" id="totalPage">
                </ul>
            </div>
        </div>
    </div>
        <%--信息框--%>
        <div id="messageModal" style="position: fixed;left:0;right: 70%;z-index: 1030;bottom: 0;margin-bottom: 0;top:10%;margin-top: 0;background-color:white ;OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
            <div class="navbar-inner">
            <div id="objectMessage">
                <label style="font-size: 18px;margin-left: 0%" id="labelText">设施信息</label>
                <button style="float: right;display: none" onclick="getObjectMessage()" id="returnObject"><span class="glyphicon glyphicon-chevron-left"></span></button>
                <table class="table table-bordered table-hover" style="width:100%;" id="objectMessageTable"> <!-- table table-bordered 带边框的样式 -->
                </table>
            </div>

            <div style="text-align:center;">
                <ul id="message" <%--style="float: left"--%>>
                </ul>
                <ul class="pagination" id="totalPage1">
                </ul>
            </div>
            </div>
        </div>
</div>
</div>

<div class="container">
    <div id="picSlider" class="modal fade">
        <div class="modal-dialog" style="width: 340px">
            <div class="modal-content">
                <!-- 模态框 -->
                <div id="myCarousel" class="carousel slide">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators" id="picNum">

                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner" id="picImg">

                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="carousel-control left" href="#myCarousel"
                       data-slide="prev">‹</a>
                    <a class="carousel-control right" href="#myCarousel"
                       data-slide="next">›</a>
                </div>
            </div>
        </div>
    </div>
    <p><a data-toggle="modal" href="#example" class="btn btn-primary btn-large">Launch demo</a></p>
</div>
</body>
</html>

<script>
    /*var viewer = new Cesium.Viewer("cesiumContainer", {
        animation: false,  //是否显示动画控件
        baseLayerPicker: false, //是否显示图层选择控件
        geocoder: true, //是否显示地名查找控件
        timeline: false, //是否显示时间线控件
        sceneModePicker: true, //是否显示投影方式控件
        navigationHelpButton: false, //是否显示帮助信息控件
        infoBox: false,  //是否显示点击要素之后显示的信息
        imageryProvider : new Cesium.WebMapTileServiceImageryProvider({
            url: "http://t0.tianditu.com/vec_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=vec&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles",
            layer: "tdtVecBasicLayer",
            style: "default",
            format: "image/jpeg",
            tileMatrixSetID: "GoogleMapsCompatible",
            show: false
        })
    });
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
        url: "http://t0.tianditu.com/cva_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cva&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default.jpg",
        layer: "tdtAnnoLayer",
        style: "default",
        format: "image/jpeg",
        tileMatrixSetID: "GoogleMapsCompatible"
    }));*/

    var url =  'http://{s}.tianditu.com/img_w/wmts?service=WMTS&version=1.0.0&request=GetTile&tilematrix={TileMatrix}&layer=img&style={style}&tilerow={TileRow}&tilecol={TileCol}&tilematrixset={TileMatrixSet}&format=tiles';
    var viewer = new Cesium.Viewer('cesiumContainer', {
        imageryProvider : new Cesium.WebMapTileServiceImageryProvider({
            url :url,
            layer : 'img',
            style : 'default',
            format : 'tiles',
            tileMatrixSetID : 'w',
            credit : new Cesium.Credit('天地图全球影像服务'),
            subdomains : ['t0','t1','t2','t3','t4','t5','t6','t7'],
            maximumLevel : 18
        }),
        baseLayerPicker : false,
        infoBox: false,
    });

    var imageryLayers = viewer.imageryLayers;
    var url2 =  'http://{s}.tianditu.com/cia_w/wmts?service=WMTS&version=1.0.0&request=GetTile&tilematrix={TileMatrix}&layer=cia&style={style}&tilerow={TileRow}&tilecol={TileCol}&tilematrixset={TileMatrixSet}&format=tiles';
    var labelImagery = new Cesium.WebMapTileServiceImageryProvider({
        url : url2,
        layer : 'cia',
        style : 'default',
        format : 'tiles',
        tileMatrixSetID : 'w',
        credit : new Cesium.Credit('天地图全球影像中文注记服务'),
        subdomains : ['t0','t1','t2','t3','t4','t5','t6','t7']
    });
    var a=imageryLayers.addImageryProvider(labelImagery);
    a.brightness=1.5;
    //var viewer = new Cesium.Viewer('cesiumContainer');
    /*var viewer = new Cesium.Viewer("cesiumContainer", {
        animation: false,  //是否显示动画控件
        baseLayerPicker: false, //是否显示图层选择控件
        geocoder: true, //是否显示地名查找控件
        timeline: false, //是否显示时间线控件
        sceneModePicker: true, //是否显示投影方式控件
        navigationHelpButton: false, //是否显示帮助信息控件
        infoBox: false,  //是否显示点击要素之后显示的信息
        imageryProvider: new Cesium.WebMapTileServiceImageryProvider({
            url: "http://t0.tianditu.com/img_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=img&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles",
            layer: "tdtBasicLayer",
            style: "default",
            format: "image/jpeg",
            tileMatrixSetID: "GoogleMapsCompatible",
            show: false,
            maximumLevel : 18
        })
    });
    viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
        url: "http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default.jpg",
        layer: "tdtAnnoLayer",
        style: "default",
        format: "image/jpeg",
        tileMatrixSetID: "GoogleMapsCompatible",
        show: false,
        maximumLevel : 18
    }));*/

    var rectangle = new Cesium.Rectangle.fromDegrees(114.3 , 30.5, 114.4, 30.6);
    viewer.camera.flyTo({
        destination: rectangle
    });

    $(function () {
        $(document.body).css({
            "overflow-x":"hidden",
            "overflow-y":"hidden"
        });
        if (typeof Cesium !== "undefined") {
            addCanlianData(Cesium);
        } else if (typeof require === "function") {
            require(["Cesium"], addCanlianData);
        }
        var height=$(".page-header").height;
        $("#messageModal").css("top",""+height+"px");
        getObjectMessage();
        /*/!*加载残联数据*!/
        $("#canlian").click(function () {
            if ($(this).is(":checked")) {
                if (typeof Cesium !== "undefined") {
                    addCanlianData(Cesium);
                } else if (typeof require === "function") {
                    require(["Cesium"], addCanlianData);
                }
               //addCanlianData();
            } else {
                layers.remove(blackMarble1);
                //viewer.dataSources.remove(datasource);
            }
        });
        /!*加载天门数据*!/
        $("#tianmen").click(function () {
            if ($(this).is(":checked")) {
                if (typeof Cesium !== "undefined") {
                    addTianmenData(Cesium);
                } else if (typeof require === "function") {
                    addTianmenData(["Cesium"], addTianmenData);
                }
            } else {
                layers.remove(blackMarble);
            }
        });
        /!*控制左侧栏*!/
        $(".panel-heading").click(function(e){
            /!*切换折叠指示图标*!/
            $(this).find("span").toggleClass("glyphicon-chevron-down");
            $(this).find("span").toggleClass("glyphicon-chevron-up");
        });*/

    })

    function addCanlianData(Cesium) {

        var canlianUrl='http://101.89.183.113:8080/geoserver/map/wms'; //Geoserver URL
        layers = viewer.scene.globe.imageryLayers;
        //layers.removeAll();
        blackMarble1=layers.addImageryProvider(new Cesium.WebMapServiceImageryProvider({
            url : canlianUrl,
            layers: 'map:objectMessage',// Here just give layer n
            version:'1.1.0',
            format:"image/png",
            style: "default",
        }));
        //var a=Cesium.WebMapServiceImageryProvider.GetFeatureInfoDefaultParameters;

        var scene = viewer.scene;
        var handler = new Cesium.ScreenSpaceEventHandler(scene.canvas);
        var mark=0;
        viewer.scene.camera.moveEnd.addEventListener(function(){
            if( Math.ceil(viewer.camera.positionCartographic.height)<100000){
                if(mark==0){
                    /*添加子类图层*/
                    mark=1;
                    var canlianUrl='http://101.89.183.113:8080/geoserver/map/wms'; //Geoserver URL
                    layers = viewer.scene.globe.imageryLayers;
                    //layers.removeAll();
                    blackMarble2=layers.addImageryProvider(new Cesium.WebMapServiceImageryProvider({
                        url : canlianUrl,
                        layers: 'map:attributeMessage',// Here just give layer n
                        version:'1.1.0',
                        format:"image/png",
                    }));
                    //var a=Cesium.WebMapServiceImageryProvider.GetFeatureInfoDefaultParameters;
                }

            }else if (Math.ceil(viewer.camera.positionCartographic.height)>100000){
                layers.remove(blackMarble2);
                mark=0;
            }
        });
        handler.setInputAction(function(evt) {
            cancelShowTheObjectTable();
            $("#pointMessage").css("display","block");
            var ray=viewer.camera.getPickRay(evt.position);
            var featuresPromise = viewer.imageryLayers.pickImageryLayerFeatures(ray, viewer.scene);
            if (!Cesium.defined(featuresPromise)) {
                alert('No features picked.');
            } else {
                Cesium.when(featuresPromise, function(features) {
                    // This function is called asynchronously when the list if picked features is available.
                   // alert('Number of features: ' + features.length);
                    var tbody="<tr style='background:#fff;'><th style='font-size: 12px'>序号</th><th style='font-size: 12px'>名字</th><th style='font-size: 12px'>地址</th><th style='font-size: 12px'>时间</th><th style='font-size: 12px'>状态</th><th style='font-size: 12px'>图片</th></tr>";
                    if(features.length==0){
                        var trs = "<tr><td align='center' colspan='6'>无记录</td></tr>";
                        tbody+=trs;
                    }else{
                        for (var i = 0; i <10&&i<features.length; i++) {//拼接对应<th>需要的值
                            var trs = "";
                            var picUrl=features[i].properties.url;
                            var img="<img src='<%=basepath%>"+picUrl+"' width='50px' height='50px' />";
                            var name=features[i].properties.name;
                            var x=features[i].data.geometry.coordinates[0];
                            var y=features[i].data.geometry.coordinates[1];
                            var isPass=features[i].properties.isPass;
                            var state="";
                            if(isPass==1){
                                state="可使用";
                            }else if(isPass==2){
                                state="不可用";
                            }else if(isPass==3){
                                state="改建中"
                            }
                            trs+="<tr ><td onclick='loadThepoint("+x+","+y+")'>" +(Number)(1+i)
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" +features[i].properties.name
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" + features[i].properties.address
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" + features[i].properties.createTime
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" + state
                                + "</td><td>" + "<button onclick='getPic(\""+picUrl+"\")'>查看图片</button>"
                                +"</td></tr>";
                            tbody+=trs;
                        };
                    }
                    $("#pointMessageTable").html(tbody);
                    //var currentPage = data.CurrentPage; //当前页数
                    var pageCount = Math.ceil(features.length/10); //总页数
                    /*$("#otherOption").empty().append(" <!--<li style='float:left;'><a style='color: #ffffff;background-color: #6399c7;border-color: #337ab7'>共计1页</a></li> -->" +
                        "<li style='float:left;'><a style='color: #ffffff;background-color: #6399c7;border-color: #337ab7'>跳转到</a><input style='width: 40px' id='pageNum'></li> " +
                        "<li style='float:left;'><a href='javascript:void(0)' onclick='goToThePage()' style='color: #ffffff;background-color: #6399c7;border-color: #337ab7'>确定</a></li>")*/
                    $("#totalPage").empty().append("<li><a>共"+pageCount+"页</a></li>")
                    var options = {
                        bootstrapMajorVersion: 3, //版本
                        currentPage: 1, //当前页数
                        totalPages: pageCount, //总页数
                        numberOfPages:5,
                        shouldShowPage:true,//是否显示该按钮
                        itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;

                            }
                        },//点击事件，用于通过Ajax来刷新整个list列表
                        onPageClicked: function (event, originalEvent, type, page) {
                            var tbody="<tr style='background:#fff;'><th style='font-size: 12px'>序号</th><th style='font-size: 12px'>名字</th><th style='font-size: 12px'>地址</th><th style='font-size: 12px'>时间</th><th style='font-size: 12px'>状态</th><th style='font-size: 12px'>图片</th></tr>";
                            for (var i = (page-1)*10; i <features.length&&i<page*10; i++) {//拼接对应<thn>需要的值
                                var trs = "";
                                var number=((Number)(page-1)*10+i+1);
                                var picUrl=features[i].properties.url;
                                var img="<img src='<%=basepath%>"+picUrl+"' width='50px' height='50px' />";
                                var x=features[i].data.geometry.coordinates[0];
                                var y=features[i].data.geometry.coordinates[1];
                                trs+="<tr ><td onclick='loadThepoint("+x+","+y+")'>" +(Number)(i+1)
                                    + "</td><td onclick='loadThepoint("+x+","+y+")'>" +features[i].properties.name
                                    + "</td><td onclick='loadThepoint("+x+","+y+")'>" + features[i].properties.address
                                    + "</td><td onclick='loadThepoint("+x+","+y+")'>" + features[i].properties.createTime
                                    + "</td><td onclick='loadThepoint("+x+","+y+")'>" + state
                                    + "</td><td>" + "<button onclick='getPic(\""+picUrl+"\")'>查看图片</button>"
                                    +"</td></tr>";
                                tbody+=trs;
                            };
                            $("#pointMessageTable").html(tbody);
                        }
                    };
                    $('#pointOption').bootstrapPaginator(options);
                });
            }
        }, Cesium.ScreenSpaceEventType.LEFT_CLICK);
    }

    function loadThepoint(x,y) {
        viewer.entities.removeAll();
        var wyoming = viewer.entities.add({
            name : 'Wyoming',
            position : Cesium.Cartesian3.fromDegrees(x,y),
            //name: '国家 : '+country[i] + "  |  " + '全部 : '+number[i] + "  |  " +'第一作者 : '+firstnumber[i], //点击描上的显示信息
            point : {
                color : Cesium.Color.YELLOW,
                pixelSize : 8
            },
            scale : 1,//和原始大小相比的缩放比例
            minimumPixelSize :100 //最小尺寸，防止太小而看不见
        });
        //这是一个动画效果，进入后镜头就会自动转到这个实体处
        viewer.trackedEntity = wyoming;
        //viewer.zoomTo(wyoming);
    }

    function  cancelShowTheTable() {
        $("#pointMessage").css("display","none");
        $("#messageModal").css("display","block")

    }

    function goToThePage() {

    }
    function getPic(url) {
        var numStr="";
        var sliderStr="";
        if(url==""||url==null){
            sliderStr+="<div><img src='<%=basepath%>img/erro.jpg'></div> "
        }else{
            var urls=url.split(",");
            for(var i=0;i<urls.length;i++){
                if(i==0){
                    numStr+="<li data-target='#myCarousel' data-slide-to="+i+" class='active'></li> "
                    sliderStr+="<div class='item active'><img   onerror='this.onerror=null; this.src=\"<%=basepath%>img/erro.jpg\";' src='<%=basepath%>"+urls[i]+"' ><div class='carousel-caption'>"+(Number)(i+1)+"</div></div> "
                }else{
                    numStr+="<li data-target='#myCarousel' data-slide-to="+i+"></li> "
                    sliderStr+="<div class='item'><img  onerror='this.onerror=null; this.src=\"<%=basepath%>img/erro.jpg\";' src='<%=basepath%>"+urls[i]+"' ><div class='carousel-caption'>"+(Number)(i+1)+"</div></div> "
                }

            }
        }
        $("#picNum").empty().append(numStr);
        $("#picImg").empty().append(sliderStr);
        $('#myCarousel').carousel();
        $('#myCarousel').carousel({
            interval: 1000
        })
        $("#picSlider").modal("show");
    }

    function addTianmenData(Cesium) {
        var tainmenUrl='http://120.55.62.229:38989/geoserver/tianmen/wms'; //Geoserver URL
        layers = viewer.scene.globe.imageryLayers;
        //layers.removeAll();
        blackMarble=layers.addImageryProvider(new Cesium.WebMapServiceImageryProvider({
            url : tainmenUrl,
            layers: 'tianmen:tianmenDouble',// Here just give layer n
            version:'1.1.0'
        }));
// Start off looking at china.
        /*viewer.camera.setView({
            destination: Cesium.Rectangle.fromDegrees(112.477509144,30.2959985328,113.594913432,30.9650573232)
        });//Sandcastle_End*/
    }
    function addTiandituData() {
        var imageryLayers = viewer.imageryLayers;
        var url2 =  'http://{s}.tianditu.com/cia_w/wmts?service=WMTS&version=1.0.0&request=GetTile&tilematrix={TileMatrix}&layer=cia&style={style}&tilerow={TileRow}&tilecol={TileCol}&tilematrixset={TileMatrixSet}&format=tiles';
        var labelImagery = new Cesium.WebMapTileServiceImageryProvider({
            url : url2,
            layer : 'cia',
            style : 'default',
            format : 'tiles',
            tileMatrixSetID : 'w',
            credit : new Cesium.Credit('天地图全球影像中文注记服务'),
            subdomains : ['t0','t1','t2','t3','t4','t5','t6','t7']
        });
        imageryLayers.addImageryProvider(labelImagery);
    }

    /*获取所有设施点*/
    function getObjectMessage() {
        $.ajax({
            async: true,
            type: "get",
            url: "<%=basepath%>cesium/getObjectMessage",//向后台发送请求，后台为stuts2框架
            dataType: "json",
            data: {pageNumber:'1'},
            cache: false,
            success: function(data) {
                var pageCount="";
                var tbody="<tr style='background:#fff;'><th style='font-size: 12px'>序号</th><th style='font-size: 12px'>名字</th>" +
                    "<th style='font-size: 12px'>地址</th><th style='font-size: 12px'>存储时间</th><th>图片</th><th></th></tr>";
                if(data==null){
                    var trs = "<tr><td align='center' colspan='6'>无记录</td></tr>";
                    tbody+=trs;
                    pageCount=1;
                }else{
                    for (var i = 0; i <10&&i<data.length; i++) {//拼接对应<th>需要的值
                        var messageList=data[i];
                        var url=messageList.url;
                        //var img="<img src='<%=basepath%>"+url[0]+"' width='50px' height='50px' />";
                        var trs = "";
                        var x=messageList.x;
                        var y=messageList.y;
                        trs+="<tr><td onclick='loadThepoint("+x+","+y+")'>" + (Number)(1+i)
                            + "</td><td onclick='loadThepoint("+x+","+y+")'>" + messageList.name
                            + "</td><td onclick='loadThepoint("+x+","+y+")'>" + messageList.address
                            + "</td><td onclick='loadThepoint("+x+","+y+")'>" + messageList.createTime
                            + "</td><td>" + "<button onclick='getPic(\""+url+"\")'>查看图片</button>"
                            + "</td><td>" + "<button onclick='getAttributeMessage("+messageList.objId+")'>查看子类</button>"
                            +"</td></tr>";
                        tbody+=trs;
                    };
                    pageCount= Math.ceil(data.length/10);; //总页数
                }
                $("#objectMessageTable").html(tbody);
                var currentPage = 1; //当前页数

                $("#totalPage1").empty().append("<li><a>共"+pageCount+"页</a></li>")
                var options = {
                    bootstrapMajorVersion: 3, //版本
                    currentPage: currentPage, //当前页数
                    totalPages: pageCount, //总页数
                    numberOfPages:5,
                    shouldShowPage:true,//是否显示该按钮
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;

                        }
                    },//点击事件，用于通过Ajax来刷新整个list列表
                    onPageClicked: function (event, originalEvent, type, page) {
                        var tbody="<tr style='background:#fff;'><th style='font-size: 12px'>序号</th><th style='font-size: 12px'>名字</th>" +
                            "<th style='font-size: 12px'>地址</th><th style='font-size: 12px'>存储时间</th><th>图片</th><th></th></tr>";
                        for (var i = (page-1)*10; i <data.length&&i<page*10; i++) {//拼接对应<thn>需要的值
                            var messageList=data[i];
                            var url=messageList.url;
                           // var img="<img src='<%=basepath%>"+url[0]+"' width='50px' height='50px' />";
                            var trs = "";
                            var x=messageList.x;
                            var y=messageList.y;
                            trs+="<tr ><td onclick='loadThepoint("+x+","+y+")'>" + (Number)(1+i)
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" + messageList.name
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" + messageList.address
                                + "</td><td onclick='loadThepoint("+x+","+y+")'>" + messageList.createTime
                                + "</td><td>" + "<button onclick='getPic(\""+url+"\")'>查看图片</button>"
                                + "</td><td>" + "<button onclick='getAttributeMessage("+messageList.objId+")'>查看子类</button>"
                                +"</td></tr>";
                            tbody+=trs;
                        };
                        $("#objectMessageTable").html(tbody);
                    }
                };
                $('#message').bootstrapPaginator(options);
                $("#returnObject").css("display","none");
            }/*success*/
        });
    }

    function cancelShowTheObjectTable() {
        $("#messageModal").css("display","none");
    }

    function getAttributeMessage(objId) {
        $.ajax({
            async: true,
            type: "get",
            url: "<%=basepath%>cesium/getAttributeMessage",//向后台发送请求，后台为stuts2框架
            dataType: "json",
            data: { objId: objId},
            cache: false,
            success: function (data) {
                var pageCount="";
                var tbody = "<tr style='background:#fff;'><th style='font-size: 12px'>序号</th><th style='font-size: 12px'>名字</th>" +
                    "<th style='font-size: 12px'>地址</th><th style='font-size: 12px'>存储时间</th><th>图片</th></tr>";
                if (data==null) {
                    var trs = "<tr><td align='center' colspan='5'>无记录</td></tr>";
                    tbody += trs;
                    pageCount=1;
                } else {
                    for (var i = 0; i < 10&&i<data.length; i++) {//拼接对应<th>需要的值
                        var messageList = data[i];
                        var url=messageList.url;
                        var img="<img src='<%=basepath%>"+url[0]+"' width='50px' height='50px' />";
                        var trs = "";
                        var x = messageList.x;
                        var y = messageList.y;
                        trs += "<tr><td onclick='loadThepoint(" + x + "," + y + ")'>" + (Number)(1 + i)
                            + "</td><td onclick='loadThepoint(" + x + "," + y + ")'>" + messageList.name
                            + "</td><td onclick='loadThepoint(" + x + "," + y + ")'>" + messageList.address
                            + "</td><td onclick='loadThepoint(" + x + "," + y + ")'>" + messageList.createTime
                            + "</td><td>" + "<button onclick='getPic(\""+url+"\")'>查看图片</button>"
                            + "</td></tr>";
                        tbody += trs;
                    }
                    ;
                    pageCount = Math.ceil(data.length / 10);
                }
                $("#objectMessageTable").html(tbody);
                var currentPage = 1; //当前页数
                ; //总页数
                $("#totalPage1").empty().append("<li><a>共" + pageCount + "页</a></li>")
                var options = {
                    bootstrapMajorVersion: 3, //版本
                    currentPage: currentPage, //当前页数
                    totalPages: pageCount, //总页数
                    numberOfPages: 5,
                    shouldShowPage: true,//是否显示该按钮
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;

                        }
                    },//点击事件，用于通过Ajax来刷新整个list列表
                    onPageClicked: function (event, originalEvent, type, page) {
                        var tbody = "<tr style='background:#fff;'><th style='font-size: 12px'>序号</th><th style='font-size: 12px'>名字</th>" +
                            "<th style='font-size: 12px'>地址</th><th style='font-size: 12px'>存储时间</th><th>图片</th></tr>";
                        for (var i = (page - 1) * 10; i < data.length && i < page * 10; i++) {//拼接对应<thn>需要的值
                            var messageList = data[i];
                            var url=messageList.url;
                            var img="<img src='<%=basepath%>"+url[0]+"' width='50px' height='50px' />";
                            var trs = "";
                            var x = messageList.x;
                            var y = messageList.y;
                            trs += "<tr ><td onclick='loadThepoint(" + x + "," + y + ")'>" + (Number)(1 + i)
                                + "</td><td onclick='loadThepoint(" + x + "," + y + ")'>" + messageList.name
                                + "</td><td onclick='loadThepoint(" + x + "," + y + ")'>" + messageList.address
                                + "</td><td onclick='loadThepoint(" + x + "," + y + ")'>" + messageList.createTime
                                + "</td><td>" + "<button onclick='getPic(\""+url+"\")'>查看图片</button>"
                                + "</td></tr>";
                            tbody += trs;
                        }
                        ;
                        $("#objectMessageTable").html(tbody);
                    }
                };
                $('#message').bootstrapPaginator(options);
                $("#ilabelText").html("子类设施信息");
                $("#returnObject").css("display","block");
            }
        })
    }

    /*  var scene = viewer.scene;
      var handler = new Cesium.ScreenSpaceEventHandler(scene.canvas);
      handler.setInputAction(function(click){
          startup();
      },Cesium.ScreenSpaceEventType.LEFT_CLICK);*/

    /*viewer = new Cesium.Viewer('cesiumContainer',{
        animation: false,
        baseLayerPicker: false,
        geocoder: true,
        timeline: false,
        sceneModePicker: true,
        navigationHelpButton: false,
        infoBox: true,
        imageryProvider : new Cesium.WebMapTileServiceImageryProvider({
            url: "http://t0.tianditu.com/vec_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=vec&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default&format=tiles",
            layer: "tdtVecBasicLayer",
            style: "default",
            format: "image/jpeg",
            tileMatrixSetID: "GoogleMapsCompatible",
            show: false
        })
    });
    function startup(Cesium) {

        viewer.imageryLayers.addImageryProvider(new Cesium.WebMapTileServiceImageryProvider({
            url: "http://t0.tianditu.com/cva_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cva&tileMatrixSet=w&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}&style=default.jpg",
            layer: "tdtAnnoLayer",
            style: "default",
            format: "image/jpeg",
            tileMatrixSetID: "GoogleMapsCompatible"
        }));
       // var widget = new Cesium.CesiumWidget('cesiumContainer');
        url='http://120.55.62.229:38989/geoserver/tianmen/wms'; //Geoserver URL
        layers = viewer.scene.globe.imageryLayers;
        //layers.removeAll();
        blackMarble=layers.addImageryProvider(new Cesium.WebMapServiceImageryProvider({
            url : url,
            layers: 'tianmen:tianmenDouble',// Here just give layer n
            version:'1.1.0'
        }));
        blackMarble.alpha = 0.7;//给一个透明度
        blackMarble.brightness = 1.0;
// Start off looking at china.
        viewer.camera.setView({
            destination: Cesium.Rectangle.fromDegrees(112.477509144,30.2959985328,113.594913432,30.9650573232)
        });//Sandcastle_End
        addMultiplePoints();

        Sandcastle.finishedLoading();
    }
    if (typeof Cesium !== "undefined") {
        startup(Cesium);
    } else if (typeof require === "function") {
        require(["Cesium"], startup);
    }

    function addMultiplePoints() {
        var wyoming = viewer.entities.add({
            name : 'Wyoming',
            position : Cesium.Cartesian3.fromDegrees(112.477509144,30.2959985328),
            //name: '国家 : '+country[i] + "  |  " + '全部 : '+number[i] + "  |  " +'第一作者 : '+firstnumber[i], //点击描上的显示信息
            point : {
                color : Cesium.Color.YELLOW,
                pixelSize : 8
            }
        });
        //这是一个动画效果，进入后镜头就会自动转到这个实体处
        viewer.zoomTo(wyoming);
        wyoming.description ="zhongguo"
    }*/
    /*var url =  'http://{s}.tianditu.com/img_w/wmts?service=WMTS&version=1.0.0&request=GetTile&tilematrix={TileMatrix}&layer=img&style={style}&tilerow={TileRow}&tilecol={TileCol}&tilematrixset={TileMatrixSet}&format=tiles';
    var viewer = new Cesium.Viewer('cesiumContainer', {
        imageryProvider : new Cesium.WebMapTileServiceImageryProvider({
            url :url,
            layer : 'img',
            style : 'default',
            format : 'tiles',
            tileMatrixSetID : 'w',
            credit : new Cesium.Credit('天地图全球影像服务'),
            subdomains : ['t0','t1','t2','t3','t4','t5','t6','t7'],
            maximumLevel : 18
        }),
        baseLayerPicker : false
    });

    var imageryLayers = viewer.imageryLayers;
    var url2 =  'http://{s}.tianditu.com/cia_w/wmts?service=WMTS&version=1.0.0&request=GetTile&tilematrix={TileMatrix}&layer=cia&style={style}&tilerow={TileRow}&tilecol={TileCol}&tilematrixset={TileMatrixSet}&format=tiles';
    var labelImagery = new Cesium.WebMapTileServiceImageryProvider({
        url : url2,
        layer : 'cia',
        style : 'default',
        format : 'tiles',
        tileMatrixSetID : 'w',
        credit : new Cesium.Credit('天地图全球影像中文注记服务'),
        subdomains : ['t0','t1','t2','t3','t4','t5','t6','t7']
    });
    imageryLayers.addImageryProvider(labelImagery);


    function cancelGeocode(viewModel) {
        viewModel._isSearchInProgress = false;
        if (Cesium.defined(viewModel._geocodeInProgress)) {
            viewModel._geocodeInProgress.cancel = true;
            viewModel._geocodeInProgress = undefined;
        }
    }

    function updateCamera(viewModel, destination) {
        viewModel._scene.camera.flyTo({
            destination : destination,
            complete: function() {
                viewModel._complete.raiseEvent();
            },
            duration : viewModel._flightDuration,
            endTransform : Cesium.Matrix4.IDENTITY
        });
    }

    function geocode(viewModel) {
        var query = viewModel.searchText;

        if (/^\s*$/.test(query)) {
            //whitespace string
            return;
        }

        // If the user entered (longitude, latitude, [height]) in degrees/meters,
        // fly without calling the geocoder.
        var splitQuery = query.match(/[^\s,\n]+/g);
        if ((splitQuery.length === 2) || (splitQuery.length === 3)) {
            var longitude = +splitQuery[0];
            var latitude = +splitQuery[1];

            var obj = GPS.gcj_decrypt_exact(latitude,longitude);
            var height = (splitQuery.length === 3) ? +splitQuery[2] : 300.0;

            if (!isNaN(longitude) && !isNaN(latitude) && !isNaN(height)) {
                updateCamera(viewModel, Cesium.Cartesian3.fromDegrees(obj.lon,obj.lat, height));
                return;
            }
        }
        viewModel._isSearchInProgress = true;

        var smPOI = 'http://www.supermapol.com/iserver/services/localsearch/rest/searchdatas/China/poiinfos.jsonp';
        var promise = Cesium.loadJsonp(smPOI, {
            parameters : {
                keywords : query,
                city : "北京市",
                location : '',
                radius : '',
                leftLocation : '',
                rightLocation : '',
                pageSize : 50,
                pageNum : 1,
                key:"S4lcB1k7tOv22H2paEuN7RSf" // your personal key, you can get it from SuperMap Online
            },
            callbackParameterName : 'callback',
            jsonpName : 'callBack'
        });
        var geocodeInProgress = viewModel._geocodeInProgress = Cesium.when(promise, function(result) {
            if (geocodeInProgress.cancel) {
                return;
            }
            viewModel._isSearchInProgress = false;

            if (result.length === 0 || result.totalHints === 0) {
                viewModel.searchText = viewModel._searchText + ' (not found)';
                return;
            }
            if(Cesium.defined(viewModel.entities)){
                for(var i=0;i<viewModel.entities.length;i++)
                {
                    viewer.entities.remove(viewModel.entities[i]);
                }
            }
            viewModel.entities = [];

            var obj;
            for(var i=0;i<result.poiInfos.length;i++)
            {
                var resource = result.poiInfos[i];
                viewModel._searchText = resource.name;
                var location = resource.location;

                obj = GPS.gcj_decrypt_exact(location.y,location.x);

                var entity = {
                    id:resource.name + i,
                    position : Cesium.Cartesian3.fromDegrees(obj.lon,obj.lat),
                    point : {
                        show : true, // default
                        color : Cesium.Color.SKYBLUE, // default: WHITE
                        pixelSize : 10, // default: 1
                        outlineColor : Cesium.Color.YELLOW, // default: BLACK
                        outlineWidth : 3 // default: 0
                    }
                };

                entity.description = new Cesium.ConstantProperty(resource.name);

                viewModel.entities.push(entity);
                viewer.entities.add(entity);
            }

            updateCamera(viewModel, Cesium.Cartesian3.fromDegrees(obj.lon,obj.lat, height));
        }, function() {
            if (geocodeInProgress.cancel) {
                return;
            }

            viewModel._isSearchInProgress = false;
            viewModel.searchText = viewModel._searchText + ' (error)';
        });
    }

    var geocoder = viewer.geocoder.viewModel;

    geocoder._searchCommand = Cesium.createCommand(function() {
        if (geocoder.isSearchInProgress) {
            cancelGeocode(geocoder);
        } else {
            geocode(geocoder);
        }
    });

    var scene = viewer.scene;
    var handler = new Cesium.ScreenSpaceEventHandler(scene.canvas);
    handler.setInputAction(function(click){
        startup();
    },Cesium.ScreenSpaceEventType.LEFT_CLICK);
    $(function () {

    })*/


</script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/9/5
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Hello React!</title>
    <script src="https://cdn.bootcss.com/react/16.4.0/umd/react.development.js"></script>
    <script src="https://cdn.bootcss.com/react-dom/16.4.0/umd/react-dom.development.js"></script>
    <script src="https://cdn.bootcss.com/babel-standalone/6.26.0/babel.min.js"></script>
</head>
<body>
<div id="example"></div>
<div id="example1"></div>

<script type="text/babel">
    class Clock extends React.Component{
        constructor(props){
            super(props);
            this.state={date:new Date()};
        }

        componentDidMount(){
            this.timeID=setInterval(
                ()=>this.tick(),
                1000
            )
        }

        componentWillMount(){
            clearInterval(this.timeID);
        }

        tick(){
            this.setState({
                date:new Date()
                }
            )
        }

        render(){
            return(
                <div>
                    <p>{this.state.date.toLocaleTimeString()}</p>
                </div>
            )
        }
    }



    ReactDOM.render(
        <Clock/>,
        document.getElementById("example")
    );

        class App extends Component{
        constructor(props){
        super(props);

        this.handleInputChange=this.handleInputChange.bind(this);
        this.handleTextareaChange=this.handleTextareaChange.bind(this);

        this.state={
        inputValue:'',
        textareaValue:''}};


        handleInputChange(e){
        this.setState({
        inputValue:e.target.value,
    });
    }

        handleTextareaChange(e){
        this.setState({
        textareaValue:e.target.value,
    });
    }

        render(){
        const {inputValue,textareaValue} = this.state;
        return(
            <div>
                <p>单行输入框:<input type="text" value={inputValue}
                                onChange={this.handleInputChange} /></p>
                <p>多行输入框：<textarea value={textareaValue}
                                   onChange={this.handleTextareaChange} /></p>
            </div>
        );
    }
    }

        ReactDOM.render(
        <App/>
        document.getElementById("example")
        );
</script>
</body>
</html>
--%>


