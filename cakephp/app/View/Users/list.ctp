<!-- Begin vung dieu kien tim kiem -->
<form action="/cakephp/index" method="post" name="mainform">
    <table  class="tbl_input" border="0" width="90%"  cellpadding="0" cellspacing="0" >
        <tr>
            <td>

            </td>
        </tr>
        <tr>
            <td>
            </td>
        </tr>
        <tr>
            <td width="100%">
                <table class="tbl_input" cellpadding="4" cellspacing="0" >
                    <tr>
                        <td class="lbl_left">Email:</td>
                        <td align="left">
                            <input class="txBox" type="text" name="mailSearch" value="<?php echo isset($mailSearch) ? $mailSearch: '';?>"
                                   size="20" onfocus="this.style.borderColor='#0066ff';"
                                   onblur="this.style.borderColor='#aaaaaa';" />
                        </td>
                        <td align="left">
                            <input class="btn" name="btnSearch" type="submit" value="Search" />
                        </td>
                    </tr>
                    <tr><td>
                            <b>Total User: </b>
                        </td>
                        <td> <?php echo isset($totalUser)? $totalUser:''; ?></td>
                    </tr>
                    <tr>

                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <!-- End vung dieu kien tim kiem -->
</form>
<!-- Begin vung hien thi danh sach user -->
<table class="tbl_list" border="1" cellpadding="4" cellspacing="0" width="80%">

    <tr class="tr2">
        <th align="center" width="60px">
            User ID
        </th>
        <th align="left">
            User Name
        </th>
        <th align="left">
            Email
        </th>
        <th align="left">
            BirthDay
        </th>
        <th align="left">
            Address
        </th>
        <th align="left" width="70px">
            Type
        </th>
        <th align="left">
            Action
        </th>
    </tr>
    <?php
    foreach ($dataUsers as $users) {

        if (isset($users['User']) && isset($users['UserType'])) {
            ?>
            <tr>
                <td align="right">
                    <a href="/cakephp/edit/<?php echo $users['User']['id']; ?>">
                        <?php echo $users['User']['id'] ?>
                    </a>
                </td>
                <td>
                    <?php echo $users['User']['user_name'] ?>
                </td>
                <td align="center">
                    <?php echo $users['User']['email'] ?>
                </td>
                <td>
                    <?php echo date('d-m-Y', strtotime($users['User']['birthday'])); ?>
                </td>
                <td>
                    <?php echo $users['User']['address'] ?>
                </td>
                <td>
                    <?php echo $users['UserType']['type_name'] ?>
                </td>
                <td>
                    <a href="/cakephp/edit/<?php echo $users['User']['id']; ?>">Edit</a>
                    | <?php if ($users['UserType']['type_name'] != "Admin" && $userType == 1) {
                        ?><a href="/cakephp/delete/<?php echo $users['User']['id']; ?>">Delete</a>
                    <?php } ?>
                </td>
            </tr>
            <?php
        }
    }
    ?>
</table>
<!-- End vung hien thi danh sach user -->

<!-- Begin vung paging -->
<table>
    <tr>
        <?php
        // Xử lý hiển thị html phân trang
        echo $this->Paginator->prev('<<', null, null, array('class' => 'disabled')); //Hiện thj nút Previous
        echo $this->Paginator->first(' < ');
        echo " | ".$this->Paginator->numbers()." | "; //Hiển thi các số phân trang
        echo $this->Paginator->last(' > ');
        echo $this->Paginator->next('>>', null, null, array('class' => 'disabled')); //Hiển thị nút next
        echo " Page ".$this->Paginator->counter(); // Hiển thị tổng trang
        ?>
    </tr>
</table>
<!-- End vung paging -->
<br>
<a href="/cakephp/add/"> Thêm mới</a>
<!-- Begin vung footer -->
<div class = "lbl_footer">
    <br><br><br><br>
    Copyright ©　2020　Nguyễn Thịnh Phú. All rights reserved.
</div>
<!-- End vung footer -->