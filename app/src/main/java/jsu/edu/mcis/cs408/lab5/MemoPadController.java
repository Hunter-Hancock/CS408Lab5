package jsu.edu.mcis.cs408.lab5;

import java.util.List;

public class MemoPadController extends AbstractController {

    public static final String MEMOS = "Memos";

    private final MemoPadModel model;

    public MemoPadController(MemoPadModel model) {
        super();
        this.model = model;
        this.addModel(model);
    }

    public List<Memo> getMemos() {
        return model.getMemos();
    }

    public void addMemo(Memo m) {
        model.addMemo(m);
    }

    public void deleteMemo(int id) {
        model.deleteMemo(id);
    }
}
