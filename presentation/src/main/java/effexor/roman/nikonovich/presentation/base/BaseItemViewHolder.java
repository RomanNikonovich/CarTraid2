package effexor.roman.nikonovich.presentation.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import effexor.roman.nikonovich.BR;

public abstract class BaseItemViewHolder<Model,
        ViewModel extends BaseItemViewModel,
        Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private Binding binding;
    private ViewModel viewModel;


    public BaseItemViewHolder(Binding binding, ViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;
        this.viewModel = viewModel;
        this.viewModel.init();
        initViewModel();
    }

    public void bindTo(Model model, int position) {

        viewModel.setItem(model, position);
        binding.executePendingBindings();
    }

    public void release() {
        viewModel.release();
    }

    protected void initViewModel() {
        binding.setVariable(BR.viewModel, viewModel);
    }
}
