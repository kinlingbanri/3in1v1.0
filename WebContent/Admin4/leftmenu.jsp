<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="./index.jsp">
<!--                 <div class="sidebar-brand-icon rotate-n-15"> -->
<!--                     <i class="fas fa-laugh-wink"></i> -->
<!--                 </div> -->
                <div class="sidebar-brand-text mx-3">複合式加值機<br>管理系統</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider">

                        <!-- Heading -->
<!--             <div class="sidebar-heading">營運管理</div> -->
            
            <!-- Nav Item - 店家管理 Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>營運管理</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    		<a class="collapse-item" href="./store_setting.jsp">店家配置</a>
                    		<a class="collapse-item" href="./device_state.jsp">設備狀態</a>
                        <a class="collapse-item" href="./member_setting.jsp">會員管理</a>
                        <a class="collapse-item" href="./server_setting.jsp">伺服器管理</a>
                        <a class="collapse-item" href="./qrcode.jsp">QRcode</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - 設備管理 Collapse Menu -->
<!--             <li class="nav-item"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities"> -->
<!--                     <i class="fas fa-fw fa-cog"></i> -->
<!--                     <span>設備管理</span> -->
<!--                 </a> -->
<!--                 <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                     		<a class="collapse-item" href="./addvalue_setting.jsp">加值機設定</a> -->
<!--                     		<a class="collapse-item" href="./addvalue_add.jsp">新增加值機</a> -->
<!--                     		<a class="collapse-item" href="./device_setting.jsp">消費機設定</a> -->
<!--                     		<a class="collapse-item" href="./device_add.jsp">新增消費機</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->
            
            <!-- Nav Item - 會員管理 Collapse Menu -->
<!--             <li class="nav-item"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree"> -->
<!--                     <i class="fas fa-fw fa-cog"></i> -->
<!--                     <span>會員管理</span> -->
<!--                 </a> -->
<!--                 <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                         <a class="collapse-item" href="./member_setting.jsp">會員管理</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->
            
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-table"></i>
										<span>報表統計</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="./store_statistics.jsp">統計資料</a>
                        <a class="collapse-item" href="./device_report.jsp">數據報表</a>
                    </div>
                </div>
            </li>
                        
            <!-- Nav Item - 伺服器管理 Collapse Menu -->
<!--             <li class="nav-item"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFour" aria-expanded="true" aria-controls="collapseFour"> -->
<!--                     <i class="fas fa-fw fa-cog"></i> -->
<!--                     <span>伺服器管理</span> -->
<!--                 </a> -->
<!--                 <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                     		<a class="collapse-item" href="./server_setting.jsp">參數設定</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->

            <!-- Divider -->
            <hr class="sidebar-divider">

<!--             Heading -->
<!--             <div class="sidebar-heading">營運數據</div> -->

<!--             Nav Item - 營運數據 Collapse Menu -->
<!--             <li class="nav-item"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages"> -->
<!--                     <i class="fas fa-fw fa-table"></i> -->
<!-- 										<span>報表統計</span> -->
<!--                 </a> -->
<!--                 <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                         <a class="collapse-item" href="./store_statistics.jsp.jsp">統計資料</a> -->
<!--                         <a class="collapse-item" href="./device_report.jsp">數據報表</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->

<!--             Divider -->
<!--             <hr class="sidebar-divider d-none d-md-block"> -->
            

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->