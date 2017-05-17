<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flight Center</title>
    <style>
        .wrap {
            width: 100%;
            overflow: auto;
        }

        .float-left {
            float: left;
            width: 33%;
            background: lightblue;
            height: 100%;
        }

        .float-center {
            float: left;
            width: 33%;
            background: lightgreen;
            height: 100%;
            margin-left: 0.25%;
        }

        .float-right {
            float: right;
            background: pink;
            height: 100%;
            width: 33.5%;

        }
    </style>
    <!-- jquery -->
    <!--<script src="assets/js/jquery.min.js"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {

            //for all forms on this page
            $("form").submit(function (event) {

                event.preventDefault();

                $.ajax(
                    {
                        type: $(this).attr('method'),
                        url: $(this).attr('action'),
                        data: $(this).serialize()
                    }
                ).done(function (callback) {
                    console.log(callback);
                }).fail(function (error) {
                    console.log(error);
                });

            });

        });
    </script>
</head>
<body>
<div class="wrap">
    <div class="float-left">
        //Meow
        <h1>
            Register
        </h1>
        <form action="rest/api/register" method="post">
            <table>
                <tbody>
                <tr>
                    <td>
                        <input name="name" type="text" placeholder="Full Name">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="email" type="email" placeholder="Email Address">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="password" type="password" placeholder="Password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="birthday" type="date" placeholder="Date of Birth">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Register">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
    <div class="float-center">
        <h1>
            Welcome
        </h1>
    </div>
    <div class="float-right">
        <h1>
            Login
        </h1>
        <form action="rest/api/login" method="post">
            <table>
                <tbody>
                <tr>
                    <td>
                        <input name="email" type="email" placeholder="Email Address">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="password" type="password" placeholder="Password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="submit-login" type="submit" value="Login">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>
