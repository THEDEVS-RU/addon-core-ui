package dev.qubik.coreui.view.token;

import com.vaadin.flow.router.Route;
import dev.qubik.entity.Token;
import io.jmix.flowui.view.*;


@Route(value = "tokens", layout = DefaultMainViewParent.class)
@ViewController(id = "Token.list")
@ViewDescriptor(path = "token-list-view.xml")
@LookupComponent("tokensDataGrid")
@DialogMode(width = "64em")
public class TokenListView extends StandardListView<Token> {
}