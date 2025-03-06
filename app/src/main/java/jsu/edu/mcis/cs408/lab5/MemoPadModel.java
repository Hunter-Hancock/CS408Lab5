package jsu.edu.mcis.cs408.lab5;

import android.content.Context;

import java.util.List;

public class MemoPadModel extends AbstractModel {
    private MemoPadDatabaseHelper db;
    private List<Memo> memos;

    public MemoPadModel(Context ctx) {
        super();
        db = new MemoPadDatabaseHelper(ctx);
        memos = db.listMemos();
    }

    public List<Memo> getMemos() {
        return memos;
    }

    public void addMemo(Memo m) {
        db.insertMemo(m);
        memos = db.listMemos();
        firePropertyChange(MemoPadController.MEMOS, null, memos);
    }

    public void deleteMemo(int id) {
        db.deleteMemo(id);
        memos = db.listMemos();
        firePropertyChange(MemoPadController.MEMOS, null, memos);
    }
}
