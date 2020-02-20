<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <?php
    echo $this->Html->css('/asset/css/style.css');
    ?>

    <?php
    echo $this->Html->script('/asset/js/user.js');
    ?>
    <script>
        function backUrl() {
            window.location.href = '/cakephp/index';
        }
    </script>
    <title>ユーザ管理</title>
</head>
<style>
    .on {
        width: 800px;
        margin: 0px auto;
        padding: 20px;
        border: 1px solid black;
    }
</style>
<body>
<div class="on">
    <?php
    echo $this->fetch('content');
    ?>
</div>
</body>
</html>