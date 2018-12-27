var canvas;
var stage;
var img = new Image();//声明一个图片对象
var spirte;
window.onload=function(){
    canvas=document.getElementById("canvas");
    stage = new createjs.Stage(canvas);//创建一个舞台来承载canvas
    //给舞台设置监听事件（这个动画有两个事件一个是鼠标点击的事件一个是鼠标滑过的事件，所以下面要给舞台添加两个事件）
    stage.addEventListener("stagemousedown",clickCanvas);
    //第一个参数是传递的事件对象，第二个参数是响应的方法
    stage.addEventListener("stagemousemove",moveCanvas);//第二个事件是鼠标移动的时间

    //先定义一个序列
    var data={
        images:["img/star.png"],//引入图片
        frames:{width:20,height:20,regX:10,regY:10}//创建一个序列的变化方式,regX是以中心点进行变化
    }
    //下面需要创建一个能够承载这个序列的类
    spirte = new createjs.Sprite(new createjs.SpriteSheet(data));
    //设置按照下面的时间进行改变
    createjs.Ticker.setFPS(30);
    createjs.Ticker.addEventListener("tick",tick)//设置监听事件
}
//创建一个下面的处理方法
function tick(e){//将事件传递给e
    var t = stage.getNumChildren();
    for(var i=t-1;i>0;i--){
        var s=stage.getChildAt(i);
        s.vY +=2;
        s.vX +=1;
        s.x+=s.vX;
        s.y+=s.vY;
        s.scaleX = s.scaleY = s.scaleX+s.vS;//保证x和y的缩放点一致
        s.alpha +=s.vA;//透明度的变化
        if(s.alpha<=0 || s.y>canvas.height){
            stage.removeChildAt(i);//不能一次性全部移除
        }
    }
    //每移动一下都需要对舞台进行更新
    stage.update(e);
}
//点击事件的处理函数
function clickCanvas(e){//e是需要传进来的事件参数
    addS(Math.random()*200 + 100,stage.mouseX,stage.mouseY,2);//调用下面的方法
}
//滑动事件的处理函数
function moveCanvas(e){
    addS(Math.random()*2 + 1,stage.mouseX,stage.mouseY,1);
}
//处理位置和处理的参数传递方法
function addS(count,x,y,speed){//传递的参数，数量和坐标，及弹出的范围
    for(var i=0;i<count;i++){
        var s=spirte.clone();// 每个小的图片都是一个实例对象,clone返回的是一个实例对象
        s.x = x;//分别设置点击时所出现的位置
        s.y = y;//下面+0.5是为了防止变化的过于聚集
        s.alpha = Math.random()*0.5+0.5;//星星点击时是有一个透明度的变化从亮到暗的一个过程
        s.scaleX = s.scaleY = Math.random() + 0.3;//设置缩放,保证x和y的缩放是一致的，+0.3是为了防止缩放太小
        var a = Math.PI*2*Math.random();// 设置滑动曲线
        var v = (Math.random() - 0.5)*30*speed;//设置每个星星落下的速度
        s.vX = Math.cos(a)*v;
        s.vY = Math.sin(a)*v;
        s.vS = (Math.random()-0.5) * 0.2;//这里是scale的变化
        s.vA = -Math.random() * 0.05 -0.01;//这里是alpha的变化
        stage.addChild(s);
    }
}
