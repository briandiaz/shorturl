<div class="modal fade" id="newURL" tabindex="-1" role="dialog" aria-labelledby="newURL" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Create New URL</h4>
            </div>
            <div class="modal-body">

                <form accept-charset="UTF-8" role="form" method="POST" action="./ServletURL">
                    <fieldset>
                        <div class="form-group text-center">
                            Enter your url,a  long URL is a website address such as:</br><span class="text-primary ">https://www.pucmmsti.edu.do/websise/estudiante/</span> 
                        </div>
                        <input class="form-control" name="servlet_action" id="servlet_action" type="hidden" value="create" autofocus >
                        <input class="form-control" name="url_user" id="url_user" type="hidden" value="" autofocus >
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-link"></i>
                                </div>
                                <input class="form-control" placeholder="url_full" name="url_full" id="url_full" autofocus  type="url" autofocus required pattern="https?://.+">
                            </div>
                        </div>

                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Short URL">
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
