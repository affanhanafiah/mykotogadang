package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_dashboard{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[dashboard/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="Panel1.SetLeftAndRight(0,100%x)"[dashboard/General script]
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 4;BA.debugLine="Panel1.SetTopAndBottom(0,100%y)"[dashboard/General script]
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
//BA.debugLineNum = 5;BA.debugLine="Panel2.SetLeftAndRight(0,100%x)"[dashboard/General script]
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 6;BA.debugLine="Panel2.SetTopAndBottom(0,14%y)"[dashboard/General script]
views.get("panel2").vw.setTop((int)(0d));
views.get("panel2").vw.setHeight((int)((14d / 100 * height) - (0d)));
//BA.debugLineNum = 8;BA.debugLine="Label1.SetLeftAndRight(0%x,100%x)"[dashboard/General script]
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 9;BA.debugLine="Label1.SetTopAndBottom(18%y,30%y)"[dashboard/General script]
views.get("label1").vw.setTop((int)((18d / 100 * height)));
views.get("label1").vw.setHeight((int)((30d / 100 * height) - ((18d / 100 * height))));
//BA.debugLineNum = 10;BA.debugLine="Label2.SetLeftAndRight(20%x,80%x)"[dashboard/General script]
views.get("label2").vw.setLeft((int)((20d / 100 * width)));
views.get("label2").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
//BA.debugLineNum = 11;BA.debugLine="Label2.SetTopAndBottom(3%y,10%y)"[dashboard/General script]
views.get("label2").vw.setTop((int)((3d / 100 * height)));
views.get("label2").vw.setHeight((int)((10d / 100 * height) - ((3d / 100 * height))));
//BA.debugLineNum = 13;BA.debugLine="Button4.SetLeftAndRight(10%x,90%x)"[dashboard/General script]
views.get("button4").vw.setLeft((int)((10d / 100 * width)));
views.get("button4").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 14;BA.debugLine="Button4.SetTopAndBottom(35%y,43%y)"[dashboard/General script]
views.get("button4").vw.setTop((int)((35d / 100 * height)));
views.get("button4").vw.setHeight((int)((43d / 100 * height) - ((35d / 100 * height))));
//BA.debugLineNum = 15;BA.debugLine="Button5.SetLeftAndRight(10%x,90%x)"[dashboard/General script]
views.get("button5").vw.setLeft((int)((10d / 100 * width)));
views.get("button5").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 16;BA.debugLine="Button5.SetTopAndBottom(45%y,53%y)"[dashboard/General script]
views.get("button5").vw.setTop((int)((45d / 100 * height)));
views.get("button5").vw.setHeight((int)((53d / 100 * height) - ((45d / 100 * height))));
//BA.debugLineNum = 17;BA.debugLine="Button6.SetLeftAndRight(10%x,90%x)"[dashboard/General script]
views.get("button6").vw.setLeft((int)((10d / 100 * width)));
views.get("button6").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 18;BA.debugLine="Button6.SetTopAndBottom(55%y,63%y)"[dashboard/General script]
views.get("button6").vw.setTop((int)((55d / 100 * height)));
views.get("button6").vw.setHeight((int)((63d / 100 * height) - ((55d / 100 * height))));
//BA.debugLineNum = 19;BA.debugLine="Button7.SetLeftAndRight(10%x,90%x)"[dashboard/General script]
views.get("button7").vw.setLeft((int)((10d / 100 * width)));
views.get("button7").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 20;BA.debugLine="Button7.SetTopAndBottom(65%y,73%y)"[dashboard/General script]
views.get("button7").vw.setTop((int)((65d / 100 * height)));
views.get("button7").vw.setHeight((int)((73d / 100 * height) - ((65d / 100 * height))));
//BA.debugLineNum = 21;BA.debugLine="Button8.SetLeftAndRight(10%x,90%x)"[dashboard/General script]
views.get("button8").vw.setLeft((int)((10d / 100 * width)));
views.get("button8").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 22;BA.debugLine="Button8.SetTopAndBottom(75%y,83%y)"[dashboard/General script]
views.get("button8").vw.setTop((int)((75d / 100 * height)));
views.get("button8").vw.setHeight((int)((83d / 100 * height) - ((75d / 100 * height))));
//BA.debugLineNum = 23;BA.debugLine="Button1.SetLeftAndRight(10%x,90%x)"[dashboard/General script]
views.get("button1").vw.setLeft((int)((10d / 100 * width)));
views.get("button1").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 24;BA.debugLine="Button1.SetTopAndBottom(85%y,93%y)"[dashboard/General script]
views.get("button1").vw.setTop((int)((85d / 100 * height)));
views.get("button1").vw.setHeight((int)((93d / 100 * height) - ((85d / 100 * height))));
//BA.debugLineNum = 26;BA.debugLine="ImageView1.SetLeftAndRight(3%x,18%x)"[dashboard/General script]
views.get("imageview1").vw.setLeft((int)((3d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((18d / 100 * width) - ((3d / 100 * width))));
//BA.debugLineNum = 27;BA.debugLine="ImageView1.SetTopAndBottom(2%y,12%y)"[dashboard/General script]
views.get("imageview1").vw.setTop((int)((2d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((12d / 100 * height) - ((2d / 100 * height))));
//BA.debugLineNum = 28;BA.debugLine="ImageView2.SetLeftAndRight(82%x,97%x)"[dashboard/General script]
views.get("imageview2").vw.setLeft((int)((82d / 100 * width)));
views.get("imageview2").vw.setWidth((int)((97d / 100 * width) - ((82d / 100 * width))));
//BA.debugLineNum = 29;BA.debugLine="ImageView2.SetTopAndBottom(2%y,12%y)"[dashboard/General script]
views.get("imageview2").vw.setTop((int)((2d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((12d / 100 * height) - ((2d / 100 * height))));

}
}