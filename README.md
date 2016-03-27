# LeapSketch
Sketching tool with LeapMotion

This is a desktop canvas based on LeapMotion, where you can draw multi stroke with different gestures to create anything. The
idea is to keep track of the user's hand motion with all kinds of gestures and trajectories, by which the user sets passcode 
for a mobile device. And when a new user tries to use the device, a similar set of gestures and trahectories need to be
detected to open it. We utilize Hausdorff distance for template matching, as described in this paper for template matching:
http://www.sciencedirect.com/science/article/pii/S0097849305000853
