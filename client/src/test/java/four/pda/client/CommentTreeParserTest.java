package four.pda.client;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import four.pda.client.model.AbstractComment;
import four.pda.client.model.Comment;

/**
 * Created by asavinova on 02/11/15.
 */
public class CommentTreeParserTest extends AbstractTest {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yy HH:ss");

	@Test
	public void firstComment() throws IOException {
		String pageSource = getHtmlSource("/2014/10/27/182819/");
		List<AbstractComment> comments = new CommentTreeParser().parse(pageSource);

		Assert.assertNotNull(comments);
		Assert.assertTrue(comments.size() > 0);

		Comment firstComment = (Comment) comments.get(0);
		Assert.assertEquals("Wrong comment id", 1945077, firstComment.getId());
		Assert.assertEquals("Wrong comment nickname", "Qwertymisha", firstComment.getNickname());
		Assert.assertEquals("Wrong comment content", "Норм", firstComment.getContent());
		Assert.assertEquals("Wrong comment level", 0, firstComment.getLevel());
		Assert.assertEquals("Wrong comment date", "27.10.14 13:41", DATE_FORMAT.format(firstComment.getDate()));
	}

	@Test
	public void childComments() throws IOException {
		String pageSource = getHtmlSource("/2014/10/27/182819/");
		List<AbstractComment> comments = new CommentTreeParser().parse(pageSource);

		Assert.assertNotNull(comments);
		Assert.assertTrue(comments.size() > 0);

		Comment secondComment = (Comment) comments.get(1);
		List<AbstractComment> childComments = secondComment.getChildren();

		Comment child1 = (Comment) childComments.get(0);
		Assert.assertEquals("Wrong comment id", 1945383, child1.getId());
		Assert.assertEquals("Wrong comment nickname", "keeapk", child1.getNickname());
		Assert.assertEquals("Wrong comment content", "xbrat, <br>Как ей пользоваться чего то не понял ?:)", child1.getContent());
		Assert.assertEquals("Wrong comment level", 1, child1.getLevel());
		Assert.assertEquals("Wrong comment date", "27.10.14 15:46", DATE_FORMAT.format(child1.getDate()));
	}

	@Test
	public void deletedComments() throws IOException {
		String pageSource = getHtmlSource("/2014/10/19/181576/");
		List<AbstractComment> comments = new CommentTreeParser().parse(pageSource);

		Assert.assertNotNull(comments);
		Assert.assertTrue(comments.size() > 0);

		Comment firstComment = (Comment) comments.get(0);
		List<AbstractComment> childComments = firstComment.getChildren();

		Comment child1 = (Comment) childComments.get(0);
		Assert.assertEquals("Wrong comment id", 1932557, child1.getId());
		Assert.assertEquals("Wrong comment nickname", "mrPhilL", child1.getNickname());
		Assert.assertEquals("Wrong comment content", "9900 цена. Что-то перегнули...", child1.getContent());
		Assert.assertEquals("Wrong comment level", 1, child1.getLevel());
		Assert.assertEquals("Wrong comment date", "20.10.14 01:55", DATE_FORMAT.format(child1.getDate()));

		Comment child2 = (Comment) childComments.get(1);
		Assert.assertEquals("Wrong comment id", 1932736, child2.getId());
		Assert.assertEquals("Wrong comment nickname", "RDash", child2.getNickname());
		Assert.assertEquals("Wrong comment content", "Vacum, <br>Ржать?))", child2.getContent().substring(0, 19));
		Assert.assertEquals("Wrong comment level", 1, child2.getLevel());
		Assert.assertEquals("Wrong comment date", "20.10.14 10:29", DATE_FORMAT.format(child2.getDate()));

	}

	@Test
	public void treeDeletedComments() throws IOException {
		String pageSource = getHtmlSource("/2013/12/20/130961/");
		List<AbstractComment> comments = new CommentTreeParser().parse(pageSource);

		Assert.assertNotNull(comments);
		Assert.assertEquals(35, comments.size());
	}

}