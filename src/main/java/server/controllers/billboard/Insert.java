package server.controllers.billboard;

import common.models.Billboard;
import server.router.Action;
import server.router.Request;
import server.router.models.BadRequest;
import server.router.models.IActionResult;
import server.router.models.Ok;
import server.sql.CollectionFactory;


public class Insert extends Action {

    public Insert() { }

    @Override
    public IActionResult execute(Request req) throws Exception {

        if (req.body instanceof Billboard) {
            CollectionFactory.getInstance(Billboard.class).insert((Billboard) req.body);
            return new Ok();
        }

        return new BadRequest("Not a billboard");
    }

}

