package ru.thedevs.coreui.view.token;

import com.vaadin.flow.router.Route;
import ru.thedevs.entity.Token;
import io.jmix.flowui.view.*;

@Route(value = "tokens/:id", layout = DefaultMainViewParent.class)
@ViewController(id = "Token.detail")
@ViewDescriptor(path = "token-detail-view.xml")
@EditedEntityContainer("tokenDc")
public class TokenDetailView extends StandardDetailView<Token> {
}