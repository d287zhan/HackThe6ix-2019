import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.kevin.app.abstract_objects.EnemyObject;
import com.kevin.app.ids.EnemyId;
import com.kevin.app.ids.ObjectIds;

public class Patrick extends EnemyObject {

    private int size = 200;

    public Patrick(float x, float y, ObjectIds ObjectId, EnemyId enemyId) {
        super(x, y, ObjectId, enemyId);
        // TODO Auto-generated constructor stub
    }

	@Override
    public void render(Graphics2D g) {
    }

    @Override
    public void tick() {

    }

    @Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, size, size);
	}
    
}