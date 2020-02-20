<!-- Begin vung input-->
<?php echo $this->Form->create('User', ['type' => 'post']); ?>
<span style="float:left;"><u>管理システム</u></span>
<table  class="tbl_input"   border="0" width="75%"  cellpadding="0" cellspacing="0" >
    <tr>
        <td class="errMsg">
            <div style="padding-left:120px">

                <?php if (isset($dataError) && !empty($dataError)) {
                    foreach ($dataError as $key => $value) {
                        echo $value[0]. "</br>";
                    }
                } ?>

            </div>
        </td>
    </tr>
    <tr>
        <td align="left" >
            <div style="padding-left:100px;">
                <table border="0" width="100%" class="tbl_input" cellpadding="4" cellspacing="0" >
                    <tr>
                        <td class="lbl_left">User Name<font color = "red">*</font></td>
                        <td align="left">
                            <input class="txBox" type="text" name="username" readonly value="<?php echo isset($dataUser['username']) ? $dataUser['username'] : ''; ?>"
                                   size="30" onfocus="this.style.borderColor='#0066ff';"
                                   onblur="this.style.borderColor='#aaaaaa';" />
                        </td>
                    </tr>
                    <tr>
                        <td class="lbl_left">Email<font color = "red">*</font> </td>
                        <td align="left">
                            <input class="txBox" type="text" name="email" <?php echo isset( $dataUser['readonly']) ?  $dataUser['readonly']: '';?>
                                   value="<?php echo isset($dataUser['email']) ? $dataUser['email'] : ''; ?>"
                                   size="30" onfocus="this.style.borderColor='#0066ff';"
                                   onblur="this.style.borderColor='#aaaaaa';" />
                        </td>
                    </tr>
                    <tr>
                        <td class="lbl_left">Address<font color = "red">*</font> </td>
                        <td align="left">
                            <input class="txBox" type="text" name="address"  <?php echo isset( $dataUser['readonly']) ?  $dataUser['readonly']: '';?>
                                   value="<?php echo isset($dataUser['address']) ? $dataUser['address'] : ''; ?>"
                                   size="30" onfocus="this.style.borderColor='#0066ff';"
                                   onblur="this.style.borderColor='#aaaaaa';" />
                        </td>
                    </tr>
                    <tr>
                        <td class="lbl_left">Birthday<font color = "red">*</font> </td>
                        <td align="left">
                            <input class="txBox" type="text" name="birthday" <?php echo isset( $dataUser['readonly']) ?  $dataUser['readonly']: '';?>
                                   value="<?php echo isset($dataUser['birthday']) ? date('d-m-Y', strtotime($dataUser['birthday'])) : ''; ?>"
                                   size="30" onfocus="this.style.borderColor='#0066ff';"
                                   onblur="this.style.borderColor='#aaaaaa';" />
                        </td>
                    </tr>
                    <tr>
                        <td class="lbl_left">Member Type<font color = "red">*</font></td>
                        <td style="float: left;">
                            <input  type="radio" name="type" value="Admin" <?php echo isset( $dataUser['readonly']) ?  'disabled': '';?> <?php if (isset($dataUser['type'])) echo ($dataUser['type'] == 'Admin') ? "checked" : ''; ?>
                            > Admin
                        </td>
                        <td align="left" style="float: left;">
                            <input  type="radio" name="type" value="User" <?php echo isset( $dataUser['readonly']) ?  'disabled': '';?>
                                <?php if (isset($dataUser['type'])) echo ($dataUser['type'] == 'User') ? "checked" : ''; ?>
                            > User
                        </td>
                    </tr>
                    <tr>
                    </tr>
            </div>
</table>
</div>
</td>
</tr>
</table>
<!-- Begin vung button -->
<div style="padding-left:200px;">
    <table border="0" cellpadding="4" cellspacing="0" width="300px">
        <tr>
            <th width="120px" align="center">&nbsp;</th>
            <td style="display: <?php echo isset( $dataUser['readonly']) ? 'none' : ''; ?>">
                <input class="btn" type="submit" value="Edit" />
            </td>
            <td>
                <input class="btn" id="btnBack" type="button" value="Back" onclick="backUrl()"/>
            </td>
        </tr>
    </table>
</div>
<!-- End vung button -->
<?php echo $this->Form->end(); ?>
<!-- End vung input -->

<!-- Begin vung footer -->
<div class = "lbl_footer">
    <br><br><br><br>
    Copyright ©　2020　Nguyễn Thịnh Phú. All rights reserved.
</div>
<!-- End vung footer -->