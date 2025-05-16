import { useState, useEffect } from 'react';

const BubbleBackground = () => {
    const [bubbles, setBubbles] = useState<any[]>([]);

    const createBubble = (x: number, y: number) => {
        const id = Date.now();
        const size = 20 + Math.random() * 80;
        const duration = 5 + Math.random() * 5;
        const left = x;
        const top = y;

        const newBubble = { id, left, top, size, duration };
        setBubbles((prev) => [...prev, newBubble]);

        setTimeout(() => {
            setBubbles((prev) => prev.filter((b) => b.id !== id));
        }, duration * 1000);
    };

    useEffect(() => {
        const handleClick = (e: MouseEvent) => {
            createBubble(e.clientX, e.clientY);
        };
        window.addEventListener('mousemove', handleClick);
        return () => window.removeEventListener('mousemove', handleClick);
    }, []);

    return (
        <div className="absolute inset-0 overflow-hidden z-0">
            {bubbles.map((bubble) => (
                <div
                    key={bubble.id}
                    style={{
                        left: bubble.left,
                        top: bubble.top,
                        width: bubble.size,
                        height: bubble.size,
                        animationDuration: `${bubble.duration}s`,
                    }}
                    className={`absolute rounded-full bg-white bg-opacity-10 text-white text-xs flex items-center justify-center animate-bubble font-['Press_Start_2P',_monospace]`}
                >
                    21
                </div>
            ))}
        </div>
    );
};

export default BubbleBackground;