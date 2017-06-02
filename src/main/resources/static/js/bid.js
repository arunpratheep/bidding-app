/**
 * Created by arun on 1/6/17.
 */
$(document).ready(function () {
    document.getElementById('toggleProfile').addEventListener('click', function () {
        [].map.call(document.querySelectorAll('.profile'), function (el) {
            el.classList.toggle('profile--open');
            $('#digit2').focus();
        });
    });

    "use strict";

    // When ready.
    $(function () {
        var $form = $(".field");
        var $input = $form.find("input");
        /* $input.on("mousedown", function (e) {
         e.stopPropagation();
         e.preventDefault();
         e.stopImmediatePropagation();
         return false;
         });*/

        $input.on("keyup", function (event) {


                // When user select text in the document, also abort.
                var selection = window.getSelection().toString();
                if (selection !== '') {
                    return;
                }

                // When the arrow keys are pressed, abort.
                if ($.inArray(event.keyCode, [38, 40, 37, 39]) !== -1) {
                    return;
                }


                var $this = $(this);

                // Get the value.
                var input = $this.val();

                var input = input.replace(/[\D\s\._\-]+/g, "");
                input = input ? parseInt(input, 10) : "";

                $this.val(function () {
                    return ( input.toLocaleString("en-US"));
                });
                if (input != "") {

                    var index = $('.input').index(this) + 1;
                    $('.input').eq(index).focus();
                }

                if ((event.which == 8 || event.which == 46) && $(this).val() == '') {
                    var index = $('.input').index(this) - 1;
                    if (index >= 0) {
                        $('.input').eq(index).focus();
                    }
                }
                /*var indexthis = $('.input').index(this);
                 var index0 = $('.input').index(0).val();
                 var index1 = $('.input').index(1).val();
                 var index2 = $('.input').index(2).val();
                 var index3 = $('.input').index(3).val();
                 if (indexthis == 3) {

                 }*/
            }
        );
        event.preventDefault();
    });


})
;