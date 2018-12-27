 /*文件名：Ued.Mask.js
         *作者：Mingyu.Yuan
         *开始日期：2013-5-21
         *文件简介：jQuery 插件  遮罩层
         *插件版本：v1.0
         */
        (function ($) {
                var mask = function () {
                        //版权信息
                        this.Info = {
                                Name: "Ued.Mask",
                                Version: "v1.0",
                                Author: "Mingyu.Yuan",
                                Email: "mingyuhisoft@163.com",
                                Brief: "前端开发插件库-Mask插件：遮罩层，可根据选择器选择的元素进行遮罩",
                                toString: function () {
                                        return "[Name:" + this.Name + ",Vesion:" + this.Version + ",Author:" + this.Author + ",Email:" + this.Email + ",Brief:" + this.Brief + "]";
                                }
                        };

                        this.ID = "Ued_Mask_" + parseInt(Math.random() * 100 * 100);
                        this.DefaultOps = {
                                PowerOff: false,                              //定时关闭
                                OffTime: 3 * 1000                            //默认关闭时间3秒
                        };
                        this.Timer = {};
                        this.BaseHTML = "<div id='' style='background:#000;overflow:hidden; z-index:999; opacity: 0.00; filter: alpha(opacity=0);position:absolute; left:0px; top:0px; width:100%; height:100%;'></div>";
                        this.Dom = {};
                        this.HTML = "";
                        this.Base = {};
                        this.BorderHTML = "<div id='' style='z-index:1000;text-align:center; margin:0px auto;overflow:hidden;position:absolute; left:0px; top:0px; width:100%; height:100%;'></div>";
                        this.Border = {};
                        this.ValueHTML = "<div id=''></div>";
                        this.HideMask = function () {
                                $("#" + this.ID).hide();
                                $("#" + this.ID + "_BorderHTML").hide();
                        };
                        this.ShowMask = function () {
                            $("#" + this.ID).show();
                            $("#" + this.ID + "_BorderHTML").show();
                        };
                        this.RemoveMask = function () {
                                $("#" + this.ID).remove();
                                $("#" + this.ID + "_BorderHTML").remove();
                        };
                        this.HideMaskAll = function () {
                                $("[id^=Ued_Mask_]").hide();
                                $("[id$=_BorderHTML]").hide();
                        };
                        this.RemoveMaskAll = function () {
                                $("[id^=Ued_Mask_]").remove();
                                $("[id$=_BorderHTML]").remove();
                        };
                        this.CallBack = function () { };    //回调函数

                        return this;
                };

                $.fn.extend({
                        Mask: function (html, ops,callback) {
                                var result = new mask();
                                result.HTML = typeof html == "undefined" ? "" : (typeof html == "string" || typeof html == "number"?html:"");

                                if (typeof ops == "object") {
                                        $.extend(result.DefaultOps, ops);
                                }

                                if (typeof callback == "function") {
                                        result.CallBack = callback;
                                }

                                if (this.length > 0) {
                                        result.Dom = this;

                                        //遮罩
                                        var base = $(result.BaseHTML);
                                        base.attr("id", result.ID);
                                        base.css("width", this.width() + "px");
                                        base.css("height", this.height() + "px");
                                        if (this.html() != $(document).html() && this.html() != $("body").html()) {
                                                base.css("left", this.offset().left);
                                                base.css("top", this.offset().top);
                                        }
                                        $("body").append(base);
                                        result.Base = base;

                                        //边框
                                        var border = $(result.BorderHTML);
                                        border.attr("id", result.ID + "_BorderHTML");
                                        border.css("width", this.width() + "px");
                                        border.css("height", this.height() + "px");
                                        if (this.html() != $(document).html() && this.html() != $("body").html()) {
                                                border.css("left", this.offset().left);
                                                border.css("top", this.offset().top);
                                        }
                                        $("body").append(border);
                                        result.Border = border;


                                        //内容
                                        var value = $(result.ValueHTML);
                                        value.attr("id", result.ID + "_ValueHTML");
                                        value.html(result.HTML);
                                        $("body").append(value);
                                        //使内容居中
                                        if (value.width() > border.width()) {
                                                if (value.height() > border.height()) {
                                                        
                                                } else {
                                                        var marginTop = parseInt((border.height() - value.height()) / 2);
                                                        value.css("margin-top", marginTop + "px");
                                                }
                                        } else {
                                                if (value.height() > border.height()) {
                                                        var marginLeft = parseInt((border.width() - value.width()) / 2);
                                                        value.css("margin-left", marginLeft + "px");
                                                } else {
                                                        var marginTop = parseInt((border.height() - value.height()) / 2);
                                                        value.css("margin-top", marginTop + "px");
                                                        var marginLeft = parseInt((border.width() - value.width()) / 2);
                                                        value.css("margin-left", marginLeft + "px");
                                                }
                                        }
                                        border.append(value);
                                }

                                //定时关闭
                                if (result.DefaultOps.PowerOff) {
                                        result.Timer = setTimeout(function () {
                                                result.HideMask();
                                        }, result.DefaultOps.OffTime);
                                }

                                result.CallBack();//执行回调函数
                                return result;
                        }
                });
        })(jQuery);