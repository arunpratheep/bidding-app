/**
 * Created by arun on 1/6/17.
 */
$(document).ready(function () {
    document.getElementById('toggleProfile').addEventListener('click', function () {
        [].map.call(document.querySelectorAll('.profile'), function (el) {
            el.classList.toggle('profile--open');
        });
    });
});