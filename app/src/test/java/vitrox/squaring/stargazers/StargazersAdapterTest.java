package vitrox.squaring.stargazers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import vitrox.squaring.stargazers.Adapters.StargazersAdapter;
import vitrox.squaring.stargazers.Model.GitResponse;

import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class StargazersAdapterTest {

    private StargazersAdapter adapter;
    private StargazersAdapter.ViewHolder holder;
    private View mockView;

    @Mock
    private MainActivity mockActivity;


    @Before
    public void setUp() throws Exception {
        adapter = new StargazersAdapter(RuntimeEnvironment.application);
        mockView = mock(View.class);
    }

    @Test
    public void itemCount() {
        GitResponse response = new GitResponse();
        GitResponse response2 = new GitResponse();
        List<GitResponse> responses = new ArrayList<>();
        responses.add(response);
        responses.add(response2);
        adapter.addData(responses);
        Assert.assertEquals(adapter.getItemCount(), 2);
    }

    @Test
    public void clearItems() {
        GitResponse response = new GitResponse();
        GitResponse response2 = new GitResponse();
        List<GitResponse> responses = new ArrayList<>();
        responses.add(response);
        responses.add(response2);
        adapter.addData(responses);
        adapter.resetData();
        Assert.assertEquals(adapter.getItemCount(), 0);
    }

    @Test
    public void shouldBeDataGoodOnViewHolder() {
        GitResponse response = new GitResponse();
        response.setLogin("spongeBob");
        List<GitResponse> responses = new ArrayList<>();
        responses.add(response);
        adapter.addData(responses);
        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //We have a layout especially for the items in our recycler view. We will see it in a moment.
        View listItemView = inflater.inflate(R.layout.item_row, null, false);
        holder = adapter.new ViewHolder(listItemView);
        adapter.onBindViewHolder(holder, 0);
        Assert.assertEquals(holder.mTitleView.getText(), "spongeBob");
        int drawableResId = Shadows.shadowOf(holder.mThumbImageView.getDrawable()).getCreatedFromResId();
        Assert.assertEquals(drawableResId, R.drawable.placeholder);
    }

}