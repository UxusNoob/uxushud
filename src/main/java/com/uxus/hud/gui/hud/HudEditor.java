package com.uxus.hud.gui.hud;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import static com.uxus.hud.UxusHud.*;

public class HudEditor extends Screen {
    private boolean isDragging, isDraggingf, isDraggingt, isDraggingp;

    public HudEditor() {
        super(Text.of("HUD EDITOR"));
        isDragging = false;
        isDraggingf = false;

    }

//cords, facing

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        /*toggleButton = new ToggleButton(100, 50, "a", (button) -> {
            // Button action when clicked
            toggleButton.setToggled(!toggleButton.isToggled());
        }, false); // Initial state is set to off

        addDrawableChild(toggleButton);*/


        fill(matrices, sprinthudx, sprinthudy, sprinthudx + 70, sprinthudy + 10, 0x171817);
        fill(matrices, fpsx, fpsy, fpsx + 40, fpsy + 10, 0x171817);
        fill(matrices, tpsx, tpsy, tpsx + 50, tpsy + 10, 0x171817);
        fill(matrices, pingx, pingy, pingx + 50, pingy + 10, 0x171817);

        mc.textRenderer.drawWithShadow(matrices, "HUD EDITOR", width/2, 30, color);

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && mouseX >= sprinthudx && mouseX < sprinthudx + 100 && mouseY >= sprinthudy && mouseY < sprinthudy + 100) {
            // Start dragging the rectangle
            isDragging = true;
            return true;
        }

        if (button == 0 && mouseX >= fpsx && mouseX < fpsx + 100 && mouseY >= fpsy && mouseY < fpsy + 100) {
            // Start dragging the rectangle
            isDraggingf = true;
            return true;
        }

        if (button == 0 && mouseX >= tpsx && mouseX < tpsx + 100 && mouseY >= tpsy && mouseY < tpsy + 100) {
            // Start dragging the rectangle
            isDraggingt = true;
            return true;
        }
        if (button == 0 && mouseX >= pingx && mouseX < pingx + 100 && mouseY >= pingy && mouseY < pingy + 100) {
            // Start dragging the rectangle
            isDraggingp = true;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {

        isDragging = false; isDraggingf = false; isDraggingt = false; isDraggingp = false;

        return super.mouseReleased(mouseX, mouseY, button);


    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isDragging) {
            sprinthudx = (int) mouseX - 35;
            sprinthudy = (int) mouseY - 5;
            return true;
        }
        if (isDraggingf) {
            fpsx = (int) mouseX - 20;
            fpsy = (int) mouseY - 5;
            return true;
        }
        if (isDraggingt) {
            tpsx = (int) mouseX - 25;
            tpsy = (int) mouseY - 5;
            return true;
        }
        if (isDraggingp) {
            pingx = (int) mouseX - 25;
            pingy = (int) mouseY - 5;
            return true;
        }


        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }


}
