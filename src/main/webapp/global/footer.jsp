<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- ### $App Screen Footer ### -->
<footer class="bdT ta-c p-30 lh-0 fsz-sm c-grey-600">
    <span>Copyright PokE-tra © 2023.</span>
</footer>

<script src="${pageContext.request.contextPath}/assets/jquery-validation/lib/jquery-3.1.1.js"></script>

<script src="${pageContext.request.contextPath}/assets/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/jquery-validation/additional-methods.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/jquery-validation/localization/messages_fr.min.js"></script>

<script>
    $(function() {
        $('#form').validate({
            errorElement: 'span',
            errorPlacement: function(error, element) {
                error.addClass('invalid-feedback');
                element.closest('div').append(error);
            },
            highlight: function(element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function(element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });
    });
</script>