<!-- Modal -->
<div class="modal fade" id="esModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Elasticsearch配置</h4>
            </div>
            <div class="modal-body">

                <div class="box-body">

                    <form  id="es-form">
                        <input type="hidden" name="id">
                        <input type="hidden" name="deploy_id">
                        <!-- elasticsearch -->

                        <div class="form-group">
                            <label>index name</label>
                            <input type="text"  name="index_name" class="form-control" >
                        </div>


                        <div class="form-group">
                            <label>cluster name</label>
                            <input type="text"  name="cluster_name" class="form-control" >
                        </div>

                        <div class="form-group" >
                            <label>cluster nodes</label>
                            <input type="text" name="cluster_nodes"  class="form-control" >
                        </div>

                        <div class="form-group" >
                            <label>xpack.security.user:</label>
                            <input type="text" name="xpack_security_user"  class="form-control" >
                            <font color="red">es无密码请留空，有密码账号密码以:分隔，如elastic:123456</font>
                        </div>

                    </form>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default test-es">测试</button>
                <button type="button" class="btn btn-primary" id="es-save-btn">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
