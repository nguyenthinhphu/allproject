<div class="users form">
    <?php echo $this->Form->create('User', ['type' => 'post']); ?>
    <center>
        <span style="float:left;"><u>管理システム</u></span>
        <table class="tbl_input" cellpadding="4" cellspacing="0" width="400px">
            <tr>
                <th width="120px">&nbsp;</th><th></th>
            </tr>

            <tr>
                <td class="errMsg" colspan="2"><?php if (isset($dataError) && !empty($dataError)) {
                        foreach ($dataError as $key => $value) {
                            echo $value[0]. "</br>";
                        }
                    } ?></td>
            </tr>
            <tr align="left">
                <td class="lbl_left">Email:</td>
                <td align="left">
                    <input class="txBox" type="text" name="email" value="<?php echo isset($dataUser['email']) ? $dataUser['email'] : ''; ?>" size="40" onfocus="this.style.borderColor='#0066ff';"
                           onblur="this.style.borderColor='#aaaaaa';" />
                </td>
            </tr>
            <tr>
                <td class="lbl_left">Password:</td>
                <td align="left">
                    <input class="txBox" type="password" name="password" value="<?php echo isset($dataUser['password']) ? $dataUser['password'] : ''; ?>"
                           size="40" onfocus="this.style.borderColor='#0066ff';"
                           onblur="this.style.borderColor='#aaaaaa';" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">
                    <input class="btn btn_wider" type="submit" value="ログイン" />
                </td>
            </tr>
        </table>
    </center>
    <?php echo $this->Form->end(); ?>
</div>