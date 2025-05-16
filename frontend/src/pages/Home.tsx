import UploadForm from '../components/UploadForm';
import BubbleBackground from '../components/BubbleBackground';

const Home = () => {
    return (
        <div className="relative min-h-screen bg-gradient-to-r from-blue-500 via-purple-600 to-pink-500 overflow-hidden flex items-center justify-center p-4">
            <BubbleBackground />
            <div className="z-10 w-full max-w-md p-8 bg-white bg-opacity-90 backdrop-blur-md rounded-2xl shadow-xl">
                <h1 className="text-3xl font-bold text-center mb-6 text-transparent bg-clip-text bg-gradient-to-r from-purple-700 to-blue-500">
                    ClassWatch
                </h1>
                <UploadForm />
            </div>
        </div>
    );
};

export default Home;