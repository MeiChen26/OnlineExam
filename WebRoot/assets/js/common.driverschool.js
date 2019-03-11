/**
 * Created by apple on 2017/9/19.
 */
$(function () {
    $('.left-menu-div ul>li>a').mouseover(function () {
        $(this).parent('li').addClass('hover')
        $(this).mouseout(function () {
            $(this).parents('li').removeClass('hover')
        })
        ;
    }).click(function () {
        var that = $(this);
        if (that.parent('li').hasClass('active')) {
            that.parent('li').removeClass('active');
            that.parent('li').find('.active').each(function () {
                if(this.tagName == "UL"){
                    $(this).slideUp()
                }
                $(this).removeClass('active')

            })
        } else {
            that.parent('li').siblings().each(function () {
                if ($(this).hasClass('active')) {
                    $(this).removeClass('active')
                    $(this).find('.active').each(function () {
                        if(this.tagName == "UL"){
                            $(this).slideUp()
                        }
                        $(this).removeClass('active')
                    });
                }
            });
            that.parent('li').addClass('active')
            that.parent('li').children('ul').addClass('active')
            that.parent('li').children('ul').slideDown()
        }
    });
})