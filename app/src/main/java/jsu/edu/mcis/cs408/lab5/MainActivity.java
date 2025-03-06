package jsu.edu.mcis.cs408.lab5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.beans.PropertyChangeEvent;
import java.util.List;

import jsu.edu.mcis.cs408.lab5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AbstractView {

    ActivityMainBinding binding;

    private MemoPadController controller;

    private EditText memoInput;
    private Button addButton;
    private Button deleteButton;

    private RecyclerViewAdapter adapter;

    private Integer selectedMemoId;

    private MemoPadItemClickHandler itemClick = new MemoPadItemClickHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        memoInput = binding.memoInput;
        addButton = binding.addBtn;
        deleteButton = binding.deleteBtn;
        deleteButton.setEnabled(false);

        MemoPadModel model = new MemoPadModel(this.getApplicationContext());
        controller = new MemoPadController(model);

        controller.addView(this);

        List<Memo> initMemos = controller.getMemos();
        adapter = new RecyclerViewAdapter(this, initMemos);
        binding.output.setHasFixedSize(true);
        binding.output.setLayoutManager(new LinearLayoutManager(this));
        binding.output.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String name = memoInput.getText().toString();
            Memo m = new Memo(name);
            controller.addMemo(m);
            memoInput.setText("");
        });

        deleteButton.setOnClickListener(v -> {
            if (selectedMemoId != null) {
                controller.deleteMemo(selectedMemoId);
                deleteButton.setEnabled(false);
            }
        });
    }

    public MemoPadItemClickHandler getItemClick() {
        return itemClick;
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if (propertyName.equals(MemoPadController.MEMOS)) {
            List<Memo> memos = (List<Memo>) evt.getNewValue();
            adapter = new RecyclerViewAdapter(this, memos);
            binding.output.setAdapter(adapter);
        }
    }

    private class MemoPadItemClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = binding.output.getChildLayoutPosition(v);
            RecyclerViewAdapter adapter = (RecyclerViewAdapter) binding.output.getAdapter();
            if (adapter != null) {
                Memo memo = adapter.getMemo(position);
                selectedMemoId = memo.getId();
                deleteButton.setEnabled(true);
            }
        }
    }
}